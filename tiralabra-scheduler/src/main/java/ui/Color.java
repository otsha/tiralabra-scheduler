package ui;

public enum Color {
    SUCCESS("\u001B[32m"), 
    ERROR("\u001B[31m"), 
    INFO("\u001B[34m"), 
    WARNING("\u001B[33m");
    
    private String colorCode;
    
    private Color(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getCode() {
        return colorCode;
    }
}
