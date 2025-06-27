public class FactoryTest {
    public static void main(String[] args) {
        testFactory(new WordFactory(), "Word");
        testFactory(new PdfFactory(), "PDF");
        testFactory(new ExcelFactory(), "Excel");
    }

    private static void testFactory(DocumentFactory factory, String type) {
        System.out.println("\n=== Creating " + type + " Document ===");
        Document doc = factory.createDocument();
        doc.open();
        doc.save();
    }
}