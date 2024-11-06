public class bill {
    private int id_bill = 0;
    public static int quantitybill = 0;
    public bill(){
        id_bill++;
        quantitybill++;
    }
    public int getId_bill() {
        return id_bill;
    }
}
