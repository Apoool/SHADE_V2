package Core;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
//THIS WILL BE THE DATABASE UNTIL IM COMFORTABLE WITH USING ANY MYSQL DATABASES
public class ProductData {

    public static void createFile() {
        File file = new File("D:\\Coding\\01_Java\\SHADE_V2\\src\\Core\\data.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File Exist");
        }
    }

    public static List<Product> fillObject() throws IOException{
        int i = 1;
        int j = 0;

        String type = "", brand = "", location = "";
        Double price = 0.0;
        Integer stock = 0;
        Integer id = 0;
        String imagePath = "";
        String information = "";
        Integer inCart = 0;

        List<Product> products = new ArrayList<>();
        Product product;

        File file = new File("D:\\Coding\\01_Java\\SHADE_V2\\src\\Core\\data.txt");
        Path path = Paths.get("D:\\Coding\\01_Java\\SHADE_V2\\src\\Core\\data.txt");

        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            while ( j < Files.lines(path).count()) {
                String s = raf.readLine();
                if(s.indexOf("Id :") > -1){
                    id = Integer.parseInt(s.replace("Id :", ""));
                }else if(s.indexOf("Brand :") > -1){
                    brand = String.valueOf(s.replace("Brand :", ""));
                }else if(s.indexOf("Price :") > -1){
                    price = Double.valueOf(s.replace("Price :", ""));
                }else if(s.indexOf("Type :") > -1){
                    type = String.valueOf(s.replace("Type :", ""));
                }else if(s.indexOf("Location :") > -1){
                    location = String.valueOf(s.replace("Location :", ""));
                }else if(s.indexOf("Stock :") > -1){
                    stock = Integer.parseInt(s.replace("Stock :", ""));
                }else if(s.indexOf("ImagePath :") > -1){
                    imagePath = String.valueOf(s.replace("ImagePath :", ""));
                }else if(s.indexOf("Information :") > -1){
                    information = String.valueOf(s.replace("Information :", ""));
                }else if(s.indexOf("InCart :") > -1){
                    inCart = Integer.parseInt(s.replace("InCart :", ""));
                }

                if((j > 0) && (i%9 == 0)){
                    product = new Product();
                    product.setId(id);
                    product.setBrand(brand);
                    product.setPrice(price);
                    product.setType(type);
                    product.setLocation(location);
                    product.setStock(stock);
                    product.setImagePath(imagePath);
                    product.setInformation(information);
                    product.setInCart(inCart);
                    products.add(product);
                }
                i++;
                j++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
    //Transforming TXTFILE DATA to Array of Object(product)

    public static void addToCartDB(Integer changeID, Integer changeInCart) throws IOException {
        /**
        * BUFFERED PATH FOR IMMEDIATE CLEAN UP OF TXTFILE DB
         * MOVE TEMPORILY YOURE TXTFILEDB in LIST
         * FINAL WHILE LOOP FOR RETRIEVING DATA FROM LIST TO TXTFLE DB AGAIN
        * */
        int i = 0;
        int j = 0;
        File file = new File("D:\\Coding\\01_Java\\SHADE_V2\\src\\Core\\data.txt");
        Path path = Paths.get("D:\\Coding\\01_Java\\SHADE_V2\\src\\Core\\data.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        while ( j < Files.lines(path).count()){
            if(changeID == Integer.parseInt(fileContent.get(j).replace("Id :",""))){
                j += 8;
                Integer inCart = Integer.parseInt(fileContent.get(j).replace("InCart :",""));
                int k = inCart + (changeInCart);
                fileContent.set(j, ("InCart :" + k));
                break;
            }
            j+=9;
        }

        int max = (int) Files.lines(path).count();
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(String.valueOf(path)));
        writer.write("");
        writer.flush();
        j = 0;
        while (j < max){
            raf.writeBytes(fileContent.get(j) + "\n");
            j++;
        }

    }
    //To update the TXTFILE data specifically the InCart

    public static List<Product> fillCart() throws IOException{
        int i = 1;
        int j = 0;

        String type = "", brand = "", location = "";
        Double price = 0.0;
        Integer stock = 0;
        Integer id = 0;
        String imagePath = "";
        String information = "";
        Integer inCart = 0;

        List<Product> products = new ArrayList<>();
        Product product;

        File file = new File("D:\\Coding\\01_Java\\SHADE_V2\\src\\Core\\data.txt");
        Path path = Paths.get("D:\\Coding\\01_Java\\SHADE_V2\\src\\Core\\data.txt");

        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            while ( j < Files.lines(path).count()) {
                String s = raf.readLine();
                if(s.indexOf("Id :") > -1){
                    id = Integer.parseInt(s.replace("Id :", ""));
                }else if(s.indexOf("Brand :") > -1){
                    brand = String.valueOf(s.replace("Brand :", ""));
                }else if(s.indexOf("Price :") > -1){
                    price = Double.valueOf(s.replace("Price :", ""));
                }else if(s.indexOf("Type :") > -1){
                    type = String.valueOf(s.replace("Type :", ""));
                }else if(s.indexOf("Location :") > -1){
                    location = String.valueOf(s.replace("Location :", ""));
                }else if(s.indexOf("Stock :") > -1){
                    stock = Integer.parseInt(s.replace("Stock :", ""));
                }else if(s.indexOf("ImagePath :") > -1){
                    imagePath = String.valueOf(s.replace("ImagePath :", ""));
                }else if(s.indexOf("Information :") > -1){
                    information = String.valueOf(s.replace("Information :", ""));
                }else if(s.indexOf("InCart :") > -1){
                    inCart = Integer.parseInt(s.replace("InCart :", ""));
                }

                if((j > 0) && (i%9 == 0)){
                    if(inCart != 0){
                        product = new Product();
                        product.setId(id);
                        product.setBrand(brand);
                        product.setPrice(price);
                        product.setType(type);
                        product.setLocation(location);
                        product.setStock(stock);
                        product.setImagePath(imagePath);
                        product.setInformation(information);
                        product.setInCart(inCart);
                        products.add(product);
                    }

                }
                i++;
                j++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
    //    //Transforming TXTFILE DATA to Array of Object(product) specifically for CART

    public static void test(){
        List<Product> products = new ArrayList<>();
        Product product;

        product = new Product();
        product.setBrand("555");
        product.setId(1);
        product.setPrice(23.00);
        product.setType("Sardines");
        product.setLocation("Aisle 1");
        product.setStock(35);
        product.setImagePath("/Products/555Green.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Jack n' Jill");
        product.setId(2);
        product.setPrice(24.00);
        product.setType("Chips");
        product.setLocation("Aisle 1");
        product.setStock(35);
        product.setImagePath("/Products/ChippyChilinCheese.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Clear");
        product.setId(3);
        product.setPrice(25.00);
        product.setType("Shampoo");
        product.setLocation("Aisle 1");
        product.setStock(35);
        product.setImagePath("/Products/ClearMenCoolSportMenthol.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Lucky Me");
        product.setId(4);
        product.setPrice(26.00);
        product.setType("Noodles");
        product.setLocation("Aisle 1");
        product.setStock(23);
        product.setImagePath("/Products/LuckyMeKalamansi.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

//        product = new Product();
//        product.setBrand("Lucky Me");
//        product.setId(5);
//        product.setPrice(27.00);
//        product.setType("Noodles");
//        product.setLocation("Aisle 1");
//        product.setStock(3);
//        product.setImagePath("/Products/LuckyMeOriginal.png");
//        product.setInformation("This is a sample information of a product.");
//        product.setInCart(0);
//        products.add(product);

        product = new Product();
        product.setBrand("Mega");
        product.setId(6);
        product.setPrice(28.00);
        product.setType("Sardines");
        product.setLocation("Aisle 1");
        product.setStock(33);
        product.setImagePath("/Products/MegaGreen.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Mega");
        product.setId(7);
        product.setPrice(28.00);
        product.setType("Sardines");
        product.setLocation("Aisle 1");
        product.setStock(38);
        product.setImagePath("/Products/MegaRed.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Lucky Me");
        product.setId(8);
        product.setPrice(28.00);
        product.setType("Noodles");
        product.setLocation("Aisle 1");
        product.setStock(73);
        product.setImagePath("/Products/NoodlesChicken.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Jack n' Jill");
        product.setId(9);
        product.setPrice(28.00);
        product.setType("Chips");
        product.setLocation("Aisle 1");
        product.setStock(53);
        product.setImagePath("/Products/NovaCheddarCheese.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Payless");
        product.setId(10);
        product.setPrice(28.00);
        product.setType("Noodles");
        product.setLocation("Aisle 1");
        product.setStock(73);
        product.setImagePath("/Products/PaylessExtraHot.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Payless");
        product.setId(11);
        product.setPrice(28.00);
        product.setType("Noodles");
        product.setLocation("Aisle 1");
        product.setStock(63);
        product.setImagePath("/Products/PaylessSweetSpicy.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Jack n' Jill");
        product.setId(12);
        product.setPrice(28.00);
        product.setType("Chips");
        product.setLocation("Aisle 1");
        product.setStock(43);
        product.setImagePath("/Products/PiattosCheese.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("Jack n' Jill");
        product.setId(13);
        product.setPrice(28.00);
        product.setType("Chips");
        product.setLocation("Aisle 1");
        product.setStock(53);
        product.setImagePath("/Products/PiattosNachoPizza.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

        product = new Product();
        product.setBrand("555");
        product.setId(14);
        product.setPrice(28.00);
        product.setType("Sardines");
        product.setLocation("Aisle 1");
        product.setStock(93);
        product.setImagePath("/Products/YoungsTownGreen.png");
        product.setInformation("This is a sample information of a product.");
        product.setInCart(0);
        createDB(product);

    }
    //PRELOADED DATA

    public static void createDB(Product product){

        createFile();
        File file = new File("D:\\Coding\\01_Java\\SHADE_V2\\src\\Core\\data.txt");
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            while (raf.readLine() != null){
                raf.readLine();
            }
            raf.writeBytes("Id :" + product.getId()
                    + "\nBrand :" + product.getBrand()
                    + "\nPrice :" + product.getPrice()
                    + "\nType :" + product.getType()
                    + "\nLocation :" + product.getLocation()
                    + "\nStock :" + product.getStock()
                    + "\nImagePath :" + product.getImagePath()
                    + "\nInformation :" + product.getInformation()
                    + "\nInCart :" + product.getInCart()
                    + "\n");
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Where DATA is LOADED ON TXT FILE

    public static void updateDB(Product product){
        //Application no ADMIN CONTROLS FOR CRUD yet
    }
}
