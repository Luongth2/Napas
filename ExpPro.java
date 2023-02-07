class ExpPro {
    int count = 0;

    void A() throws Exception {
        try {
            count++;

            try {
                count++;

                try {
                    count++;
                    throw new Exception();

                } catch (Exception ex) {
                    count++;
                    System.out.println("Exception 1 " + count);
                    throw new Exception();
                }
            } catch (Exception ex) {
                count++;
                System.out.println("Exception 2 " + count);
            }
        } catch (Exception ex) {
            count++;
            System.out.println("Exception 3 " + count);
        }

    }

    void display() {
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        ExpPro obj = new ExpPro();
        obj.A();
        obj.display();
    }
}

