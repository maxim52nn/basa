public class bookDB {
    private String authorname;
    private String authorLastName;
    private int rate;
    private int year;
    private String bookName;
    private int id;


    private int generateId(){
        String str = this.authorname+this.authorLastName + this.bookName;
        int strLen = str.length();
        int hash = 7;
        for (int i = 0; i < strLen; i++) {
            hash = hash*31 + str.charAt(i);
        }
        return  hash;
    }

    public bookDB(String authorname, String authorLastName, int rate, int year, String bookName) {
        this.authorname = authorname;
        this.authorLastName = authorLastName;
        this.rate = rate;
        this.year = year;
        this.bookName = bookName;
        this.id = generateId();
    }

}
