package com.narumichan;

import java.util.ArrayList;
import java.util.Arrays;

// TODO: 2023-07-09
/*

    toUpper()
    toLower()





 */


public class StringWhiz {
    private StringBuilder str;
    private final ArrayList<Integer> indexes = new ArrayList<>();
    
    private StringWhiz(String str) {
        this.str = new StringBuilder(str);
        resetIndexes();
    }
    
    @Override
    public String toString() {
        resetIndexes();
        return str.toString();
    }
    
    public static StringWhiz of(String str) {
        return new StringWhiz(str);
    }
    
    public StringWhiz append(String word) {
        str.append(word);
        resetIndexes();
        return this;
    }
    
    public StringWhiz prepend(String word) {
        str.insert(0, word);
        resetIndexes();
        return this;
    }
    
    public StringWhiz at(int index) {
        indexes.clear();
        indexes.add(index);
        return this;
    }
    
    public StringWhiz beforeEvery(String word) {
        indexes.clear();
        int index = 0;
        while ((index = str.indexOf(word, index)) != -1) {
            indexes.add(index);
            index += word.length();
        }
        return this;
    }
    
    public StringWhiz beforeEveryNth(String word, int occurrence) {
        indexes.clear();
        Arrays.stream(getEveryNth(word, occurrence)).forEach(indexes::add);
        return this;
    }
    
    public StringWhiz afterEveryNth(String word, int occurrence) {
        indexes.clear();
        Arrays.stream(getEveryNth(word, occurrence)).map(value -> value + word.length()).forEach(indexes::add);
        return this;
    }
    
    public StringWhiz afterEvery(String word) {
        indexes.clear();
        int index = -1;
        while ((index = str.indexOf(word, index + 1)) != -1) {
            index += word.length();
            indexes.add(index);
        }
        return this;
    }
    
    public StringWhiz beforeNth(String word, int occurrence) {
        indexes.clear();
        int index = 0;
        int currentOccurrence = 1;
        while ((index = str.indexOf(word, index)) != -1) {
            if (currentOccurrence == occurrence) {
                indexes.add(index);
                return this;
            }
            currentOccurrence++;
            index += word.length();
        }
        indexes.add(-1);
        return this;
    }
    
    public StringWhiz afterNth(String word, int occurrence) {
        indexes.clear();
        int index = -1;
        int currentOccurrence = 1;
        while ((index = str.indexOf(word, index)) != -1) {
            if (currentOccurrence == occurrence) {
                System.out.println("nth found");
                indexes.add(index + word.length());
                return this;
            }
            currentOccurrence++;
            index += word.length();
        }
        indexes.add(-1);
        return this;
    }
    
    public StringWhiz beforeFirst(String word) {
        return beforeNth(word, 1);
    }
    
    public StringWhiz afterFirst(String word) {
        return afterNth(word, 1);
    }
    
    public StringWhiz beforeLast(String word) {
        return beforeNth(word, getCount(word));
    }
    
    public StringWhiz afterLast(String word) {
        return afterNth(word, getCount(word));
    }
    
    public StringWhiz insert(String word) {
        for (int i = indexes.size() - 1; i >= 0; --i) {
            str.insert(indexes.get(i), word);
        }
        resetIndexes();
        return this;
    }
    
    public void resetIndexes() {
        indexes.clear();
        indexes.add(str.length());
    }
    
    public StringWhiz substring() {
        if (indexes.size() == 2)
            return new StringWhiz(str.substring(indexes.get(0), indexes.get(1)));
        return new StringWhiz(str.substring(indexes.get(0)));
    }
    
    public StringWhiz substring(int startIndex) {
        return new StringWhiz(str.substring(startIndex));
    }
    
    public StringWhiz substring(int startIndex, int endIndex) {
        return new StringWhiz(str.substring(startIndex, endIndex));
    }
    
    public StringWhiz between(String word1, String word2) {
        int startIndex = getFirst(word1);
        int endIndex = getFirst(word2);
        
        if ((startIndex != -1) && (endIndex != -1)) {
            indexes.clear();
            indexes.add(startIndex);
            indexes.add(endIndex);
            return this;
        }
        resetIndexes();
        return this;
    }
    
    public StringWhiz between(int startIndex, int endIndex) {
        indexes.clear();
        indexes.add(startIndex);
        indexes.add(endIndex);
        return this;
    }
    
    public int getNth(String word, int occurrence) {
        int index = 0;
        int currentOccurrence = 1;
        while ((index = str.indexOf(word, index)) != -1) {
            if (currentOccurrence == occurrence) {
                return index;
            }
            currentOccurrence++;
            index += word.length();
        }
        return -1;
    }
    
    public int[] getEveryNth(String word, int occurrence) {
        int counter = 0;
        int[] foundIndexes = new int[getCount(word) / occurrence];
        int index = 0;
        int currentOccurrence = 1;
        while ((index = str.indexOf(word, index)) != -1) {
            if (currentOccurrence % occurrence == 0) {
                foundIndexes[counter++] = index;
            }
            currentOccurrence++;
            index += word.length();
        }
        return foundIndexes;
    }
    
    public int[] getEvery(String word) {
        int counter = 0;
        int[] foundIndexes = new int[getCount(word)];
        int index = 0;
        while ((index = str.indexOf(word, index)) != -1) {
            foundIndexes[counter++] = index;
            index += word.length();
        }
        return foundIndexes;
    }
    
    public int getFirst(String word) {
        return getNth(word, 1);
    }
    
    public int getLast(String word) {
        return getNth(word, getCount(word));
    }
    
    public int getCount(String word) {
        int index = 0;
        int occurrences = 0;
        while ((index = str.indexOf(word, index)) != -1) {
            occurrences++;
            index += word.length();
        }
        return occurrences;
    }
    
    public char charAt(int index) {
        return str.charAt(index);
    }
    
    public StringWhiz reverse() {
        if (indexes.size() == 2) {
            return reverseBetween(indexes.get(0), indexes.get(1));
        }
        
        resetIndexes();
        str.reverse();
        return this;
    }
    
    public StringWhiz reverseFrom() {
        return reverseFrom(indexes.get(0));
    }
    
    public StringWhiz reverseFrom(int index) {
        resetIndexes();
        str = new StringBuilder(str.substring(0, index)
                                        + new StringBuilder(str.substring(index, str.length())).reverse());
        return this;
    }
    
    public StringWhiz reverseTo() {
        return reverseTo(indexes.get(0));
    }
    
    public StringWhiz reverseTo(int index) {
        resetIndexes();
        str = new StringBuilder(new StringBuilder(str.substring(0, index)).reverse()
                                        + str.substring(index, str.length()));
        return this;
    }
    
    public StringWhiz reverseBetween(int startIndex, int endIndex) {
        resetIndexes();
        str = new StringBuilder(str.substring(0, startIndex)
                                        + new StringBuilder(str.substring(startIndex, endIndex)).reverse()
                                        + str.substring(endIndex, str.length()));
        return this;
    }
    
    public StringWhiz fill(String word) {
        if (indexes.size() == 2) {
            return fillBetween(word, indexes.get(0), indexes.get(1));
        }
        resetIndexes();
        str = new StringBuilder(word.repeat(str.length() / word.length())
                                        + word.substring(0, str.length() % word.length()));
        return this;
    }
    
    public StringWhiz fillFrom(String word) {
        return fillFrom(word, indexes.get(0));
    }
    
    public StringWhiz fillFrom(String word, int index) {
        resetIndexes();
        int wordLength = word.length();
        int segmentLength = str.substring(index, str.length()).length();
        str = new StringBuilder(str.substring(0, index)
                                        + word.repeat(segmentLength / wordLength)
                                        + word.substring(0, segmentLength % wordLength));
        return this;
    }
    
    public StringWhiz fillTo(String word) {
        return fillTo(word, indexes.get(0));
    }
    
    public StringWhiz fillTo(String word, int index) {
        resetIndexes();
        int wordLength = word.length();
        int segmentLength = str.substring(0, index).length();
        str = new StringBuilder(word.repeat(segmentLength / wordLength)
                                        + word.substring(0, segmentLength % wordLength)
                                        + str.substring(index, str.length()));
        return this;
    }
    
    public StringWhiz fillBetween(String word, int startIndex, int endIndex) {
        resetIndexes();
        int wordLength = word.length();
        int segmentLength = str.substring(startIndex, endIndex).length();
        str = new StringBuilder(str.substring(0, startIndex)
                                        + word.repeat(segmentLength / wordLength)
                                        + word.substring(0, segmentLength % wordLength)
                                        + str.substring(endIndex, str.length()));
        return this;
    }
    
    public int length() {
        return str.length();
    }
    
    public StringWhiz replaceNth(String target, String word, int occurrence) {
        int index = getNth(target, occurrence);
        str = new StringBuilder(str.substring(0, index)
                                        + word
                                        + str.substring(index + target.length(), str.length()));
        return this;
    }
    
    public StringWhiz replaceEvery(String target, String word) {
        int index = 0;
        while ((index = str.indexOf(target, index)) != -1) {
            str = new StringBuilder(str.substring(0, index)
                                            + word
                                            + str.substring(index + target.length(), str.length()));
            index += word.length();
        }
        return this;
    }
    
    public StringWhiz replaceFirst(String target, String word) {
        return replaceNth(target, word, 1);
    }
    
    public StringWhiz replaceLast(String target, String word) {
        return replaceNth(target, word, getCount(target));
    }

    /*public StringWhiz padString(String str, int desiredLength, char paddingChar, boolean padLeft) {
        // Implementation
    }

    public StringWhiz formatString(String template, Object... args) {
        // Implementation
    }

    public static String[] splitString(String str, String delimiter) {
        // Implementation
    }*/
    
    public StringWhiz strip() {
        str = new StringBuilder(str.toString().strip());
        return this;
    }
    
    public StringWhiz strip(String word) {
        stripLeading(word);
        stripTrailing(word);
        return this;
    }
    
    public StringWhiz stripLeading() {
        str = new StringBuilder(str.toString().stripLeading());
        return this;
    }
    
    public StringWhiz stripLeading(String word) {
        if (str.toString().startsWith(word)) {
            str = new StringBuilder(str.substring(word.length()));
        }
        return this;
    }
    
    public StringWhiz stripTrailing() {
        str = new StringBuilder(str.toString().stripTrailing());
        return this;
    }
    
    public StringWhiz stripTrailing(String word) {
        if (str.toString().endsWith(word)) {
            str = new StringBuilder(str.substring(0, str.length() - word.length()));
        }
        return this;
    }
    
    public StringWhiz stripIndent() {
        str = new StringBuilder(str.toString().stripIndent());
        return this;
    }
    
    public String[] slice(int startIndex, int endIndex, int sliceSize) {
        resetIndexes();
        int numSlices = (int) Math.ceil((double) (endIndex - startIndex) / sliceSize);
        String[] out = new String[numSlices];
        String input = substring(startIndex, endIndex).toString();
        int length = input.length();
        int i = 0;
        int start = 0;
    
        while (start < length) {
            int end = Math.min(start + sliceSize, length);
            out[i++] = input.substring(start, end);
            start += sliceSize;
        }
        
        return out;
    }
    
    public String[] slice(int sliceSize) {
        if (indexes.size() == 2) {
            return slice(indexes.get(0), indexes.get(1), sliceSize);
        }
        return slice(0, str.length(), sliceSize);
    }
    
    public StringWhiz toLowerCase(int startIndex, int endIndex) {
        resetIndexes();
        str = new StringBuilder(substring(startIndex, endIndex).toString().toLowerCase());
        
        return this;
    }
    
    public StringWhiz toLowerCase() {
        if (indexes.size() == 2) {
            return toLowerCase(indexes.get(0), indexes.get(1));
        }
        return toLowerCase(0, str.length());
    }
    
    public StringWhiz toUpperCase(int startIndex, int endIndex) {
        resetIndexes();
        str = new StringBuilder(substring(startIndex, endIndex).toString().toUpperCase());
        
        return this;
    }
    
    public StringWhiz toUpperCase() {
        if (indexes.size() == 2) {
            return toUpperCase(indexes.get(0), indexes.get(1));
        }
        return toUpperCase(0, str.length());
    }

    /*public static String convertToLowerCase(String str) {
        // Implementation
    }

    public static String convertToUpperCase(String str) {
        // Implementation
    }

    public static String capitalizeString(String str) {
        // Implementation
    }

    public static boolean containsSubstring(String str, String substring) {
        // Implementation
    }

    public static int countSubstringOccurrences(String str, String substring) {
        // Implementation
    }

    public static String[] splitLines(String str) {
        // Implementation
    }

    public static boolean startsWithSubstring(String str, String prefix) {
        // Implementation
    }

    public static boolean endsWithSubstring(String str, String suffix) {
        // Implementation
    }

    public static int getStringLength(String str) {
        // Implementation
    }

    public static char getCharacterAtIndex(String str, int index) {
        // Implementation
    }

    public static String[] extractMatchesUsingRegex(String str, String regex) {
        // Implementation
    }

    // Additional advanced string manipulation methods

    public static String customStringManipulation(String str) {
        // Implementation
    }*/
    
    
}
