public class A1Question4 {

    public static void main(String[] args) {

        String s1 = "  Hello Java Programming  ";
        String s3 = "Hello Java Programming";

        // length()
        System.out.println("Length: " + s1.length());

        // isEmpty()
        System.out.println("Is Empty: " + s1.isEmpty());

        // charAt()
        System.out.println("Char at index 2: " + s1.charAt(2));

        // toString()
        System.out.println("toString(): " + s1.toString());

        // equals()
        System.out.println("Equals: " + s1.equals(s3));

        // compareTo()
        System.out.println("CompareTo: " + s1.compareTo(s3));

        // contains()
        System.out.println("Contains 'Java': " + s1.contains("Java"));

        // indexOf()
        System.out.println("IndexOf 'Java': " + s1.indexOf("Java"));

        // lastIndexOf()
        System.out.println("LastIndexOf 'a': " + s1.lastIndexOf('a'));

        // startsWith()
        System.out.println("StartsWith '  Hello': " + s1.startsWith("  Hello"));

        // endsWith()
        System.out.println("EndsWith 'Programming  ': " + s1.endsWith("Programming  "));

        // matches()
        System.out.println("Matches regex: " + s1.trim().matches("Hello.*"));

        // substring()
        System.out.println("Substring: " + s1.substring(2, 7));

        // toLowerCase()
        System.out.println("Lowercase: " + s1.toLowerCase());

        // trim()
        System.out.println("Trimmed: '" + s1.trim() + "'");

        // replace()
        System.out.println("Replace: " + s1.replace("Java", "Python"));

        // split()
        String[] words = s1.trim().split(" ");
        System.out.print("Split: ");
        for (String w : words)
            System.out.print(w + " | ");
        System.out.println();

        // join()
        String joined = String.join("-", words);
        System.out.println("Join: " + joined);

        // valueOf()
        int num = 100;
        String value = String.valueOf(num);
        System.out.println("ValueOf: " + value);
    }
}
