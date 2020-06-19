
    /* In the name of God
     * Meysam Amirsardari ------ 98106218
     * HW3_Q2 *********** Spring1399
     */

import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;


    public class Edu {

        private static int userNum ,courseNum ,courseDataNum ,c=0;
        private static int userIndex=1;
        private static String[][] users = new String[30][30];
        private static String[][] courses = new String[30][30];
        private static String[][] courseData = new String[30][30];
        private static int[] addedCoursesID = new int[30];

        public static void main(String[] args) {
            initial();
            firstPage();
        }

        public static void firstPage(){

            // General settings for frame:
            JFrame frame1 = new JFrame("edu.sharif.edu");
            frame1.setBounds(100,100,1200,700);
            frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            Container container = frame1.getContentPane();
            container.setBackground(Color.CYAN);
            Border border = BorderFactory.createLineBorder(Color.BLACK,3,true);
            JRootPane rootPane = frame1.getRootPane();
            rootPane.setBorder(border);

            LayoutManager mgr = new GroupLayout(container);
            frame1.setLayout(mgr);
            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();

            p1.setBounds(300,150,480,330);  //external panel
            p2.setBounds(300,190,400,290);  //inner panel

            Border border0,border1, border2, border3;
            border0 = BorderFactory.createLineBorder(Color.LIGHT_GRAY,1,true);  //for rootPan
            border1 = BorderFactory.createLineBorder(Color.white,2,true);       //for panel1
            border2 = BorderFactory.createLineBorder(Color.BLUE,2,true);        //for panel2

            rootPane.setBorder(border0);
            p1.setBorder(border1);
            p2.setBorder(border2);
            p1.setBackground(Color.blue);
            p2.setLayout(null);

            Font font1 = new Font(Font.SANS_SERIF,Font.BOLD,20);       // for titles
            Font font2 = new Font(Font.DIALOG_INPUT,Font.BOLD,12);     // for وارد کنیدها
            Font font3 = new Font(Font.DIALOG_INPUT,Font.ITALIC,40);   // for captcha

            //******* text Labels:
            JLabel label1 = new JLabel("بسم الله الرحمن الرحیم");
            label1.setBounds(460,10,500,30);
            label1.setFont(font1);
            label1.setForeground(Color.blue);
            frame1.add(label1);

            JLabel label2 = new JLabel("دانشگاه صنعتی شریف-معاونت آموزشی و تحصیلات تکمیلی-واحد انفورماتیک");
            label2.setBounds(290,70,1000,30);
            label2.setForeground(Color.blue);
            label2.setFont(font1);
            frame1.add(label2);

            JLabel label3 = new JLabel("سیستم آموزش");
            label3.setBounds(400,155,40,30);
            label3.setFont(font1);
            label3.setForeground(Color.WHITE);
            p1.add(label3);

            JLabel label4 = new JLabel("شناسه کاربر:");
            label4.setBounds(280,20,150,30);
            label4.setFont(font2);
            p2.add(label4);

            JLabel label5 = new JLabel("رمز عبور:");
            label5.setBounds(280,50,150,30);
            label5.setFont(font2);
            p2.add(label5);

            // for getting UserID:
            JTextField userID = new JTextField(20);
            userID.setBounds(25,27,250,22);
            userID.setBackground(Color.WHITE);
            p2.add(userID);

            //for getting UserPass:
            JPasswordField passField = new JPasswordField(15);
            passField.setBounds(75,57,200,22);
            p2.add(passField);

            //captcha maker:

            JLabel rndNum = new JLabel(Integer.toString(newCaptchaCode())) ;
            rndNum.setBounds(155,73,300,100);
            rndNum.setFont(font3);
            p2.add(rndNum);

            JButton button1 = new JButton("جدید");
            button1.setBounds(60,115,55,25);
            p2.add(button1);

            //checking captcha:
            JLabel label8 = new JLabel("متن بالا را وارد کنید:");
            label8.setBounds(200,170,300,30);
            label8.setFont(font2);
            p2.add(label8);

            JTextField getRnd = new JTextField(20);
            getRnd.setBounds(45,175,120,25);
            getRnd.setBackground(Color.WHITE);
            p2.add(getRnd);

                JButton button2 = new JButton("ورود به سیستم");
            button2.setBounds(80,225,250,40);
            button2.setFont(font1);
            button2.setBackground(Color.BLUE);
            button2.setForeground(Color.WHITE);
            border3 = BorderFactory.createLineBorder(Color.cyan,3,true);
            button2.setBorder(border3);
            p2.add(button2);

            // final sets for frame:
            frame1.add(p2);
            frame1.add(p1);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setLayout(null);
            frame1.pack();
            frame1.setBounds(100,100,1200,700);
            frame1.setVisible(true);

            //**** listeners:
            button1.addActionListener(new ActionListener() {   //captcha renew!
                @Override
                public void actionPerformed(ActionEvent e) {
                    rndNum.setText(Integer.toString(newCaptchaCode()));
                }
            }
            );

            // final input check:
            Edu.userIndex = 0;
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean flag = false;
                    if (getRnd.getText().equals(rndNum.getText())) {
                        for (int j = 1; j < Edu.userNum + 1; j++) {
                            if (userID.getText().equals(Edu.users[j][1])) {
                                Edu.userIndex = j;
                                flag = true;
                                break;
                            }
                        }
                        if (flag && (passField.getText().equals(Edu.users[userIndex][2]))) {
                            frame1.setVisible(false);
                            secondPage();
                            rndNum.setText(Integer.toString(newCaptchaCode()));
                        } else if (flag) {
                            JOptionPane.showMessageDialog(frame1,"نام کاربری وارد شده تعریف نشده است!");
                            rndNum.setText(Integer.toString(newCaptchaCode()));
                        } else {
                            JOptionPane.showMessageDialog(frame1,"نام کاربری یا رمز عبور اشتباه است!");
                            rndNum.setText(Integer.toString(newCaptchaCode()));
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame1,"کپچای نامعتبر!");
                        rndNum.setText(Integer.toString(newCaptchaCode()));
                    }
                }
            }
            );
        }

        public static int newCaptchaCode(){
            int newRnd;
            Random rand = new Random();
            newRnd = rand.nextInt(9000);
            return (newRnd+1000);
        }

        public static void secondPage(){
            // General Setting for frame:
            JFrame frame2 = new JFrame("سامانه آموزش شریف");
            frame2.setIconImage(new ImageIcon("D://logo.png").getImage());
            frame2.setBounds(100,100,1200,700);
            frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            Container container = frame2.getContentPane();
            container.setBackground(Color.cyan);
            Border border = BorderFactory.createLineBorder(Color.WHITE,3,true);
            JRootPane rootPane = frame2.getRootPane();
            rootPane.setBorder(border);

            // Setting menuBars:
            JMenuBar mb=new JMenuBar();
            JMenu menu1=new JMenu("امور ثبت نام و ترمیم");
            JMenu menu2=new JMenu("خدمات آموزشی");
            JMenu menu3=new JMenu("امور کارنامه");
            JMenu menu4=new JMenu("نظارت و ارزیابی");
            JMenu menu5=new JMenu("گزارش های تحصیلات تکمیلی");
            JMenu menu6=new JMenu("مطلوبات کاربر");

            JMenuItem i1, i2, i3, i4, i5 ,i6,i7,i8,i9;
            i1=new JMenuItem("اطلاعیه و راهنمای ثبت نام و ترمیم");
            i2=new JMenuItem("لیست دروس ارایه شده دانشکده ها در ترم جاری");
            i3=new JMenuItem("فرم مشاوره انتخاب واحد");
            i4=new JMenuItem("پرداخت اینترنتی دانشجویان");

            i5=new JMenuItem("درخواست حذف اضطراری");
            i6=new JMenuItem("ثبت اطلاعات بانکی دانشجو");
            i7=new JMenuItem("کارتابل درخواست");

            i8=new JMenuItem("وضعیت تحصیلی و ریزنمرات دانشجو");
            i9=new JMenuItem("لیست نمرات موقت و ثبت اعتراض");

            menu1.add(i1); menu1.add(i2); menu1.add(i3); menu1.add(i4);
            menu2.add(i5); menu2.add(i6); menu2.add(i7);
            menu3.add(i8); menu3.add(i9);

            mb.add(menu1); mb.add(menu2); mb.add(menu3);
            mb.add(menu4); mb.add(menu5); mb.add(menu6);
            frame2.setJMenuBar(mb);

            // ActionListeners for Menu:
            i1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   JOptionPane.showMessageDialog(frame2,"دسترسی به این بخش مجاز نمی باشد!");
                }
            }
            );
            i2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame2,"دسترسی به این بخش مجاز نمی باشد!");
                }
            }
            );
            i3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame2,"دسترسی به این بخش مجاز نمی باشد!");
                }
            }
            );
            i4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame2,"دسترسی به این بخش مجاز نمی باشد!");
                }
            }
            );
            i5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame2,"دسترسی به این بخش مجاز نمی باشد!");
                }
            }
            );
            i6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame2,"دسترسی به این بخش مجاز نمی باشد!");
                }
            }
            );
            i7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame2,"دسترسی به این بخش مجاز نمی باشد!");
                }
            }
            );
            i8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame2,"دسترسی به این بخش مجاز نمی باشد!");
                }
            }
            );
            i9.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame2,"دسترسی به این بخش مجاز نمی باشد!");
                }
            }
            );

            Font font1 = new Font(Font.SANS_SERIF,Font.BOLD,22);
            Font font2 = new Font(Font.SANS_SERIF,Font.BOLD,14);

            // text labels for titles:
            JLabel label1 = new JLabel("نیم سال دوم 99-1398");
            label1.setBounds(500 , 10 , 400 , 30);
            label1.setFont(font1);
            label1.setForeground(Color.BLACK);
            frame2.add(label1);

            JLabel label2 = new JLabel("شماره دانشجو:");
            label2.setBounds(900 , 50 , 100 , 30);
            label2.setFont(font2);
            label2.setForeground(Color.BLACK);
            frame2.add(label2);

            JLabel label22 = new JLabel(Edu.users[Edu.userIndex][1]);
            label22.setBounds(800 , 50 , 100 , 30);
            label22.setFont(font2);
            label22.setForeground(Color.blue);
            frame2.add(label22);

            JLabel label3 = new JLabel("نام و نام خانوادگی:");
            label3.setBounds(600 , 50 , 100 , 30);
            label3.setFont(font2);
            label3.setForeground(Color.BLACK);
            frame2.add(label3);

            JLabel label33 = new JLabel(Edu.users[Edu.userIndex][0]);
            label33.setBounds(500 , 50 , 100 , 30);
            label33.setFont(font2);
            label33.setForeground(Color.blue);
            frame2.add(label33);

            JLabel label4 = new JLabel("دانشکده:");
            label4.setBounds(300 , 50 , 100 , 30);
            label4.setFont(font2);
            label4.setForeground(Color.BLACK);
            frame2.add(label4);

            JLabel label44 = new JLabel(Edu.users[Edu.userIndex][3]);
            label44.setBounds(200 , 50 , 100 , 30);
            label44.setFont(font2);
            label44.setForeground(Color.blue);
            frame2.add(label44);

            JLabel label5 = new JLabel("رشته:");
            label5.setBounds(900 , 85 , 100 , 30);
            label5.setFont(font2);
            label5.setForeground(Color.BLACK);
            frame2.add(label5);

            JLabel label55 = new JLabel(Edu.users[Edu.userIndex][3]);
            label55.setBounds(800 , 85 , 100 , 30);
            label55.setFont(font2);
            label55.setForeground(Color.blue);
            frame2.add(label55);

            JLabel label6 = new JLabel("گرایش:");
            label6.setBounds(600 , 85 , 100 , 30);
            label6.setFont(font2);
            label6.setForeground(Color.BLACK);
            frame2.add(label6);

            JLabel label66 = new JLabel(Edu.users[Edu.userIndex][4]);
            label66.setBounds(500 , 85 , 100 , 30);
            label66.setFont(font2);
            label66.setForeground(Color.blue);
            frame2.add(label66);

            JLabel label7 = new JLabel("استاد راهنما:");
            label7.setBounds(300 , 85 , 100 , 30);
            label7.setFont(font2);
            label7.setForeground(Color.BLACK);
            frame2.add(label7);

            JLabel label77 = new JLabel(Edu.users[Edu.userIndex][5]);
            label77.setBounds(200 , 85 , 100 , 30);
            label77.setFont(font2);
            label77.setForeground(Color.blue);
            frame2.add(label77);

            //Location:
            JLabel locationLabel = new JLabel("فرم ثبت نام دانشجو");
            locationLabel.setBounds(0,0,100,25);
            locationLabel.setFont(font2);
            locationLabel.setOpaque(true);
            locationLabel.setForeground(Color.WHITE);
            locationLabel.setBackground(Color.LIGHT_GRAY);
            frame2.add(locationLabel);

            //Add course:
            JLabel CourseNameLabel = new JLabel("درس");
            CourseNameLabel.setBounds(900,130,100,30);
            CourseNameLabel.setFont(font2);
            frame2.add(CourseNameLabel);

            // Getting CourseID from user:
            JTextField getCourse = new JTextField();
            getCourse.setBounds(788,137,100,22);
            frame2.add(getCourse);

            JLabel groupLabel = new JLabel("گروه");
            groupLabel.setBounds(750,130,100,30);
            groupLabel.setFont(font2);
            frame2.add(groupLabel);

            // Getting group Number from user:
            String groupNums[] = {"1","2","3","4"};
            JComboBox getGroup = new JComboBox(groupNums);
            getGroup.setBounds(700,137,40,22);
            frame2.add(getGroup);

            JLabel numLabel = new JLabel("واحد");
            numLabel.setBounds(660,130,100,30);
            numLabel.setFont(font2);
            frame2.add(numLabel);

            JTextField getNum = new JTextField();
            getNum.setBounds(620,137,30,22);
            frame2.add(getNum);

            JButton addCourse = new JButton("اضافه");
            addCourse.setBounds(530,137,70,22);
            frame2.add(addCourse);

            JButton deleteCourse = new JButton("حذف");
            deleteCourse.setBounds(100,137,70,22);
            frame2.add(deleteCourse);

            DefaultTableModel tableModel = new DefaultTableModel();
            String[] titles = {"محدودیت","وضعیت","وضعیت پیشنیازی ها","زمان کلاس","زمان امتحان","نام استاد","تعداد ثبت نامی","نام","واحد","گروه","شماره درس"};
            tableModel.setColumnIdentifiers(titles);
            JTable table = new JTable(tableModel);

            // General setting for table:
            JTableHeader header = table.getTableHeader();
            header.setBounds(100,200,980,25);
            table.setBounds(100, 225, 980, 400);
            table.setRowHeight(30);
            table.setForeground(Color.BLACK);
            table.setBackground(Color.lightGray);
            table.getColumnModel().getColumn(0).setPreferredWidth(140);
            table.getColumnModel().getColumn(1).setPreferredWidth(80);
            table.getColumnModel().getColumn(2).setPreferredWidth(120);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setPreferredWidth(150);
            table.getColumnModel().getColumn(8).setPreferredWidth(40);
            table.getColumnModel().getColumn(9).setPreferredWidth(40);
            table.getColumnModel().getColumn(10).setPreferredWidth(70);
            frame2.add(table);
            frame2.add(header);

            JCheckBox[] checkBox = {
                    new JCheckBox("1"), new JCheckBox("2"),
                    new JCheckBox("3"), new JCheckBox("4"),
                    new JCheckBox("5"), new JCheckBox("6"),
                    new JCheckBox("7"), new JCheckBox("8"),
                    new JCheckBox("9"), new JCheckBox("10"),
                    new JCheckBox("11"), new JCheckBox("12"),
                    new JCheckBox("13"), new JCheckBox("14")};

            // General Sets for checkBoxes:
            for (int j = 0; j < 14 ; j++) {
                checkBox[j] = new JCheckBox();
                checkBox[j].setBounds(1080,230+(j*30),18,18);
                checkBox[j].setBackground(Color.cyan);
                frame2.add(checkBox[j]);
                checkBox[j].setVisible(false);
            }

            //Final sets for frame:
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLayout(null);
            frame2.pack();
            frame2.setBounds(100,100,1200,700);
            frame2.setVisible(true);
            c=0;

            //adding new Course to table:
            addCourse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String courseNum = getCourse.getText();
                    int vahed = Integer.parseInt(getNum.getText());
                    int group = getGroup.getSelectedIndex()+1;
                    int courseIndex = searchCourseIndexFromCourseData(courseNum,group);
                    int st = checkCourseData(courseNum, vahed, group);
                    // Add Row:
                    if(st==1) {     //If All course Datas are valid:
                        int row = searchInAddedIndexes(Integer.parseInt(courseNum));
                        if (row==-1) { // If it's a new Course:
                            String[] vector = Edu.makeVector(courseData[courseIndex]);
                            tableModel.addRow(vector);
                            Edu.addedCoursesID[tableModel.getRowCount()-1] = Integer.parseInt(courseNum);
                            checkBox[tableModel.getRowCount()-1].setVisible(true);
                        } else if(group!=Integer.parseInt(table.getValueAt(row,9).toString())) { // only renew group:
                                System.out.println(row);
                                tableModel.removeRow(row);
                                String[] vector = Edu.makeVector(courseData[courseIndex]);
                                tableModel.addRow(vector);
                                updateCourseArray(row);
                                Edu.addedCoursesID[tableModel.getRowCount() - 1] = Integer.parseInt(courseNum);
                        }
                        getCourse.setText("");
                        getNum.setText("");
                        getGroup.setSelectedIndex(0);
                    } else if(st==0) {
                        JOptionPane.showMessageDialog(frame2, "تعداد واحد وارد شده درست نمی باشد!");
                    }else if(st==-2) {
                        JOptionPane.showMessageDialog(frame2, "گروه انتخاب شده مجاز نمی باشد!");
                    }else if (st==-3){
                        JOptionPane.showMessageDialog(frame2, "ظرفیت دوره تکمیل است!");
                    }else {
                        JOptionPane.showMessageDialog(frame2,"درسی با این شماره یافت نشد!");
                    }
                }
            }
            );

            deleteCourse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int n=0;
                    int[] sellectedRows = new int[10];
                    for (int i = 0; i < table.getRowCount() ; i++) {
                        if (checkBox[i].isSelected()){
                            sellectedRows[n] = i;
                            n++;
                        }
                    }
                    for (int i = n-1; i >=0 ; i--) {
                        tableModel.removeRow(sellectedRows[i]);
                        updateCourseArray(sellectedRows[i]);
                    }
                    for (int j = 0; j < 14; j++) {
                        checkBox[j].setSelected(false);
                        checkBox[j].setVisible(false);
                    }
                    c = 0;
                    for (int j = 0; j < table.getRowCount(); j++) {
                        checkBox[j].setVisible(true);
                        c++;
                    }
                c--;
                }
            }
            );
        }

        public static void initial (){
            File file = new File("D://HWfarC.txt");
            String[][] input = new String[35][35];
            String[] inFile = new String[30];
            int i=0 ;

            try {
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine())
                    inFile[i++] = sc.nextLine();

                for (int j = 0; j < i ; j++)
                    input[j] = inFile[j].split("  ",0);

                counter(input);
                sc.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        static void counter(String[][] input){
            int i=1;
            while (!(input[i][0].equalsIgnoreCase("دروس"))) {
                Edu.users[i] = input[i].clone();
                i++;
            }
            Edu.userNum = i-1;
            i++;
            int n=0;
            int j=1;
            int k=1;
            Edu.courses[j] = input[i].clone();
            Edu.courseData[k] = input[i].clone();
            i++;
            k++;
            n++;
            while (!(input[i][0].isEmpty())&&(i<30)) {
                Edu.courseData[k] = input[i].clone();
                k++;
                if(!(input[i][0].equals(input[i-1][0]))) {
                    j++;
                    Edu.courses[j] = input[i].clone();
                    n++;
                }
                i++;
            }
            Edu.courseNum = n;
            Edu.courseDataNum = k-1;
        }

        static int searchCourseIndexFromCourses (String courseID) {
            int i;
            boolean flag=false;
            for (i = 1; i <=Edu.courseNum ; i++)
                if (courseID.equals(Edu.courses[i][0])) {
                    flag = true;
                    break;
                }
            if(flag)
                return i;
            else
                return -1;
        }

        static int searchCourseIndexFromCourseData (String courseID , int group) {
            int i;
            for (i = 1; i <=Edu.courseDataNum ; i++)
                if (courseID.equals(Edu.courseData[i][0])) {
                    if(Edu.courseData[i][1].equals(Integer.toString(group))) {
                        break;
                    }
                }
                return i;
        }

        static int findCourseCapacity (int courseIndex){
            int cc = Integer.parseInt(courseData[courseIndex][6]) - Integer.parseInt(courseData[courseIndex][5]);
            return cc;
        }

        static int groupNumChecker(String courseID, int num){
            boolean flag = false;
            for (int i = 1; i <= Edu.courseDataNum+1 ; i++) {
                if (courseID.equals(Edu.courseData[i][0])&&(num==Integer.parseInt(Edu.courseData[i][1]))){
                    flag = true;
                    break;
                }
            }
            if (flag)
                return 1;
            else
                return 0;
        }

        static int checkCourseData (String courseID, int vahed ,int group){
            int i= searchCourseIndexFromCourses(courseID);

            if(i>-1){
                if(vahed==Integer.parseInt(Edu.courses[i][2])) {
                    if(groupNumChecker(courseID,group)==1) {
                        if (findCourseCapacity(i)>0) {
                            return 1; // All parameters are consistent!
                        } else {
                            return -3; // is full!
                        }
                    }else
                        return -2; // invalid group number!
                } else {
                    return 0; // input vahed is wrong!
                }
            } else
                return -1; // courseID not found!
        }

        public static void updateCourseArray(int index){
            int[] step = new int[30];
            step = Edu.addedCoursesID.clone();
            int j=0;
            for (int i = 0; i < Edu.addedCoursesID.length ; i++) {
                if(i!=index){
                    Edu.addedCoursesID[j] = step[i];
                    j++;
                }
            }
        }

        static String[] makeVector(String[] input){     //for table Rows
            String[] vector = new String[20];
            vector[10] = input[0];
            vector[9] = input[1];
            vector[8] = input[2];
            vector[7] = input[4];
            vector[6] = Integer.toString(Integer.parseInt(input[5])+1);
            vector[5] = input[7];
            vector[4] = input[8];
            vector[3] = input[9];
            vector[2] = input[3];
            vector[1] = "گرفته شده";
            vector[0] = input[10];
            return vector;
    }

    static int searchInAddedIndexes(int courseID){
            boolean st = false;
            int i;
        for (i = 0; i < Edu.addedCoursesID.length ; i++) {
            if (addedCoursesID[i]==courseID) {
                st = true;
                break;
            }
        }
        if (st)
            return i;
        else
            return -1;
    }

    }
