import java.io.*;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {
    //Names:
//    Ibrahim saad Ibrahim // Omar Mahmoud Lotfy


    static File RoomFile = new File("Rooms.txt");
    static File StaffFile = new File("Staff.txt");
    static File ClientFile = new File("Clients.txt");
    static File ReservationFile = new File("Reservation.txt");
    static File SequenceFile = new File("Sequence.txt");
    static File ArchiveFile = new File("Archive.txt");
    static Scanner scanner = new Scanner(System.in);

    public Main() throws IOException, ClassNotFoundException {
    }

    public static void main(String []args) throws IOException, ClassNotFoundException, ParseException {

        System.out.println("\t\t\t******Welcome to our hotel System!!******");

//        ResetDataBase();
        StartApp();


    }

    public static ArrayList InputData (File fileName) throws IOException, ClassNotFoundException{
        FileInputStream FileInputStream = new FileInputStream(fileName);
        ObjectInputStream InputStream = new ObjectInputStream(FileInputStream);
        return (ArrayList)InputStream.readObject();
    }

    public static void OutputData (File fileName, ArrayList dataList) throws IOException, ClassNotFoundException{
        FileOutputStream FileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream OutputStream = new ObjectOutputStream(FileOutputStream);
        OutputStream.writeObject(dataList);
        OutputStream.close();
    }

    public static void StartApp() throws IOException, ClassNotFoundException, ParseException {
        Archive();
        System.out.println("What do you want to do?\n 1) Create Objects\n 2) Register\n 3) Cancel Reservation\n 4) Exit\n Enter (1/3): ");
        try {
            int Choice = scanner.nextInt();
            if (Choice == 1) {
                CreateObject();
            }
            else if (Choice == 2) {
                System.out.println("Let's Register");
                Reserve();
            }

            else if (Choice == 3) {
                System.out.println("Enter Room ID: \n");
                int roomID = scanner.nextInt();
                EndReservation(roomID);
                StartApp();

            }

            else if (Choice == 4) {
                System.out.println("Good Bye!!");

            }
            else {
                System.out.println("Ah!! Something went Wrong, Please make sure to enter a number from 1-3");
                StartApp();
            }
        } catch (Exception e){
            System.out.println("Ah!! Something went Wrong, Please make sure to enter a number from 1-3");
            String unUsedVar = scanner.next();
            StartApp();
        }

    }

    public static void Reserve() throws IOException, ClassNotFoundException, ParseException {
        System.out.println("What Type of Rooms do you want to reserve?\n 1) Accommodation\n 2) Office\n" +
                " 3) Hall\n 4) Meetings Room\n Enter (1/4): ");
        try{
            int TypeOfRoom = scanner.nextInt();
            if (1 <= TypeOfRoom && TypeOfRoom <= 4){
                ShowAvailableRooms(TypeOfRoom);
                System.out.println("Do you want to continue? (Y/N): ");
                char continueChoice = scanner.next().charAt(0);

                if(continueChoice == 'Y' || continueChoice == 'y'){

                    System.out.println("Enter the Room ID: ");
                    int RoomID = scanner.nextInt();
                    double PricePerDay = ReserveRoom(RoomID);
                    System.out.println("Enter Client Name: ");
                    String ClientName = scanner.next();
                    System.out.println("Enter Client Phone Number: ");
                    String ClientPhoneNumber = scanner.next();
                    ArrayList DiscountID = FetchClientID(ClientName, ClientPhoneNumber);
                    if(! DiscountID.get(0).equals(0)){

                        System.out.println("Enter Arrival Date (dd/MM/yyyy): ");
                        String ArrivalDate = scanner.next();
                        System.out.println("Enter Departure Date (dd/MM/yyyy): ");
                        String DepartureDate = scanner.next();
                        System.out.println("Enter discount: ");
                        float Discount = scanner.nextFloat();
                        float TotalDiscount = Discount +  + (float)DiscountID.get(1);
                        double TotalReceipt = CalculateReceipt(PricePerDay,ArrivalDate, DepartureDate, TotalDiscount);
                        Reservation reservation = new Reservation(RoomID, (int)DiscountID.get(0), ArrivalDate, DepartureDate,
                                Discount, TotalReceipt);
                        ArrayList<Reservation> reservationArray = InputData(ReservationFile);
                        reservationArray.add(reservation);
                        OutputData(ReservationFile, reservationArray);
                        System.out.println("Voila!! You have reserved your Room");
                        System.out.println(reservation);
                        StartApp();


                    }
                }
                else {
                    StartApp();
                }
            }
            else {
                System.out.println("Ah!! Something went Wrong, Please make sure to enter a number from 1-4");
                Reserve();
            }

        }catch (Exception e){
            System.out.println("Ah!! Something went Wrong, Please make sure to enter a number from 1-3");
            String unUsedVar = scanner.next();
            Reserve();
        }

    }

    public static ArrayList FetchClientID(String name, String phoneNumber) throws IOException, ClassNotFoundException, ParseException {
        boolean exists = false;
        float Discount = 0;
        int ClientID = 0;
        ArrayList<ArrayList> ClientsArrays = InputData(ClientFile);
        for (ArrayList<Client> clientsArray : ClientsArrays) {
            for (Client client : clientsArray) {
                if (client.getName().equals(name) && client.getPhoneNumber().equals(phoneNumber)) {
                    ClientID = client.getID();
                    exists = true;
                    if(ClientsArrays.indexOf(clientsArray) == 1 || ClientsArrays.indexOf(clientsArray) == 2){
                        Discount = ((Agent)client).getDiscount();
                    }
                }
            }
        }
        if (!exists) {
            System.out.println("This Client is here for the first time");
            CreateClient();
        }
        return new ArrayList(List.of(ClientID, Discount));
    }

    public static double ReserveRoom(int roomID) throws IOException, ClassNotFoundException, ParseException {
        boolean available = false;
        ArrayList<ArrayList> RoomsArrays = InputData(RoomFile);
        for (ArrayList<Room> roomsArray: RoomsArrays) {
            for (Room room : roomsArray) {
                if (roomID == room.getRoomID() && room.getStatus().equals("Available")) {
                    room.setStatus("Booked");
                    OutputData(RoomFile, RoomsArrays);
                    available = true;
                    return room.getPricePerDay();
                }
            }
        }
        if(!available){
            System.out.println("Wrong Room ID");
            Reserve();
        }
        return 0;
    }

    public static void ShowAvailableRooms(int TypeOfRoom) throws IOException, ClassNotFoundException, ParseException {

        if (TypeOfRoom == 1 ){
            ArrayList<AccommodationRoom> accommodationRoomArray = (ArrayList) InputData(RoomFile).get(0);
            ArrayList<AccommodationRoom> AvailableRooms = new ArrayList<>();
            for(AccommodationRoom room: accommodationRoomArray){
                if(room.getStatus().equals("Available")){
                    AvailableRooms.add(room);
                }
            }
            if(AvailableRooms.isEmpty()){
                System.out.println("There is no available Accommodations. please check later!");
                StartApp();
            }
            else{
                System.out.println(AvailableRooms);
            }


        }
        else if (TypeOfRoom == 2 ){
            ArrayList<OfficeRoom> officeRoomArray = (ArrayList) InputData(RoomFile).get(1);
            ArrayList<OfficeRoom> AvailableRooms = new ArrayList<>();
            for(OfficeRoom room: officeRoomArray){
                if(room.getStatus().equals("Available")){
                    AvailableRooms.add(room);
                }
            }
            if(AvailableRooms.isEmpty()){
                System.out.println("There is no available Office. please check later!");
                StartApp();
            }
            else{
                System.out.println(AvailableRooms);
            }
        }
        else if (TypeOfRoom == 3 ){
            ArrayList<HallRoom> hallRoomArray = (ArrayList) InputData(RoomFile).get(2);
            ArrayList<HallRoom> AvailableRooms = new ArrayList<>();
            for(HallRoom room: hallRoomArray){
                if(room.getStatus().equals("Available")){
                    AvailableRooms.add(room);
                }
            }
            if(AvailableRooms.isEmpty()){
                System.out.println("There is no available Hall. please check later!");
                StartApp();
            }
            else{
                System.out.println(AvailableRooms);
            }
        }
        else if (TypeOfRoom == 4){
            ArrayList<MeetingRoom> meetingsRoomArray = (ArrayList) InputData(RoomFile).get(3);
            ArrayList<MeetingRoom> AvailableRooms = new ArrayList<>();
            for(MeetingRoom room: meetingsRoomArray){
                if(room.getStatus().equals("Available")){
                    AvailableRooms.add(room);
                }
            }
            if(AvailableRooms.isEmpty()){
                System.out.println("There is no available Meetings Room. please check later!");
                StartApp();
            }
            else{
                System.out.println(AvailableRooms);
            }
        }
        else {
            System.out.println("Ah!! Something went Wrong, Please make sure to enter a number from 1-4");
            Reserve();
        }
    }

    public static int IncrementID(int objectIndex) throws IOException, ClassNotFoundException {
        ArrayList<Integer> sequenceArray = InputData(SequenceFile);
        int newID = sequenceArray.get(objectIndex) + 1;
        sequenceArray.set(objectIndex, newID);
        OutputData(SequenceFile, sequenceArray);
        return newID;
    }

    public static void CreateObject() throws IOException, ClassNotFoundException, ParseException {
        System.out.println("What do you want to Create?\n 1) Create Room\n 2) Create Staff Member\n" +
                " 3) Create Client\n Enter (1/3): ");
        try{
            int CreationChoice = scanner.nextInt();
            if (CreationChoice == 1 ){
                CreateRoom();
            }
            else if (CreationChoice == 2 ){
                CreateStaff();
            }
            else if (CreationChoice == 3 ){
                CreateClient();
            }
            else {
                System.out.println("Ah!! Something went Wrong, Please make sure to enter a number from 1-3");
                CreateObject();
            }

        } catch (Exception e){
            System.out.println("Ah!! Something went Wrong, Please make sure to enter a number from 1-3");
            String unUsedVar = scanner.next();
            CreateObject();
        }

    }

    public static void CreateRoom() throws IOException, ClassNotFoundException, ParseException {
        System.out.println("What type of Rooms do want to create:\n 1)Accommodation\n 2)Office\n 3)Hall\n" +
                " 4)Meetings Room\nEnter (1/4): ");
        try{
            int RoomOption = scanner.nextInt();
            System.out.println("Enter Room Number: ");
            int RoomNumber = scanner.nextInt();
            System.out.println("Enter Room Capacity: ");
            int RoomCapacity = scanner.nextInt();
            System.out.println("Enter Room Location: ");
            String RoomLocation = scanner.next();
            System.out.println("Enter Room Price per day: ");
            Double PricePerDay = scanner.nextDouble();
            System.out.println("Enter Room Rate: ");
            int RoomRate = scanner.nextInt();



            if (RoomOption == 1){
                System.out.println("Enter Room Beds Number: ");
                int RoomBeds = scanner.nextInt();
                System.out.println("Enter Room Classification:\n * Standard\n * Comfortable\n * Lux\n * DeLux\n * Sweet\n");
                String RoomClass = scanner.next();
                ArrayList<String> classOptions = new ArrayList<>(List.of("Standard", "Comfortable", "Lux", "DeLux", "Sweet"));
                if (classOptions.contains(RoomClass) ){
                    AccommodationRoom AccomRoom = new AccommodationRoom(RoomNumber, RoomCapacity, RoomLocation, PricePerDay,
                            RoomRate, RoomBeds, RoomClass);
                    AccomRoom.setRoomID(IncrementID(0));
                    ArrayList<ArrayList<Room>> RoomArray = InputData(RoomFile);
                    RoomArray.get(0).add(AccomRoom);
                    OutputData(RoomFile, RoomArray);
                    System.out.println("Voila!! Accommodation Created Successfully");
                    System.out.println(AccomRoom);
                    StartApp();
                }
                else{
                    System.out.println("You entered a wrong classification choice!");
                    CreateRoom();
                }
            }
            else if (RoomOption == 2){
                System.out.println("Enter Chairs Number: ");
                int RoomChairs = scanner.nextInt();
                System.out.println("Enter Bench Desks Number: ");
                int RoomBenchDesks = scanner.nextInt();
                System.out.println("Enter Computers Number: ");
                int RoomComputers = scanner.nextInt();
                OfficeRoom OfficeRoom = new OfficeRoom(RoomNumber, RoomCapacity, RoomLocation, PricePerDay,
                        RoomRate, RoomChairs, RoomBenchDesks, RoomComputers);
                OfficeRoom.setRoomID(IncrementID(0));
                ArrayList<ArrayList<Room>> RoomArray = InputData(RoomFile);
                RoomArray.get(1).add(OfficeRoom);
                OutputData(RoomFile, RoomArray);
                System.out.println("Voila!! Office Created Successfully");
                System.out.println(OfficeRoom);
                StartApp();
            }
            else if (RoomOption == 3){
                System.out.println("Enter Chairs Number: ");
                int RoomChairs = scanner.nextInt();
                System.out.println("Enter Stages Number: ");
                int RoomStages = scanner.nextInt();
                System.out.println("Enter Screens Number: ");
                int RoomScreens = scanner.nextInt();
                System.out.println("Is it Prepared ? (true/false): ");
                boolean IsPrepared = scanner.nextBoolean();
                HallRoom HallRoom = new HallRoom(RoomNumber, RoomCapacity, RoomLocation, PricePerDay,
                        RoomRate, RoomChairs, RoomStages, RoomScreens, IsPrepared);
                HallRoom.setRoomID(IncrementID(0));
                ArrayList<ArrayList<Room>> RoomArray = InputData(RoomFile);
                RoomArray.get(2).add(HallRoom);
                OutputData(RoomFile, RoomArray);
                System.out.println("Voila!! Hall Created Successfully");
                System.out.println(HallRoom);
                StartApp();
            }
            else if (RoomOption == 4){
                System.out.println("Enter Chairs Number: ");
                int RoomChairs = scanner.nextInt();
                System.out.println("Enter Tables Number: ");
                int RoomTables = scanner.nextInt();
                System.out.println("Enter Screens Number: ");
                int RoomScreens = scanner.nextInt();
                System.out.println("Is it Prepared ? (true/false): ");
                boolean IsPrepared = scanner.nextBoolean();
                MeetingRoom MeetingsRoom = new MeetingRoom(RoomNumber, RoomCapacity, RoomLocation, PricePerDay,
                        RoomRate, RoomChairs, RoomTables, RoomScreens, IsPrepared);
                MeetingsRoom.setRoomID(IncrementID(0));
                ArrayList<ArrayList<Room>> RoomArray = InputData(RoomFile);
                RoomArray.get(3).add(MeetingsRoom);
                OutputData(RoomFile, RoomArray);
                System.out.println("Voila!! Meetings Room Created Successfully");
                System.out.println(MeetingsRoom);
                StartApp();
            }
            else {
                System.out.println("Ah!! Something went Wrong, Please make sure to enter a number from 1-4");
                CreateRoom();
            }
        } catch (Exception e){
            System.out.println("Ah!! Something went Wrong, Please make sure to enter a proper values!!");
            String unUsedVar = scanner.next();
            CreateRoom();
        }


    }

    public static void CreateClient() throws IOException, ClassNotFoundException, ParseException {
        System.out.println("What type of Client do want to create:\n 1)Individual\n 2)Agent\n 3)Special Group\n" +
                " Enter (1/3): ");
        try{

            int ClientOption = scanner.nextInt();
            System.out.println("Enter Full Name: ");
            String FullName = scanner.next();
            System.out.println("Enter Phone Number: ");
            String PhoneNumber = scanner.next();
            System.out.println("Enter Nationality: ");
            String Nationality = scanner.next();
            System.out.println("Is The Client VIP ? (true/false): ");
            boolean IsVip = scanner.nextBoolean();

            if (ClientOption == 1){
                System.out.println("Enter Personal ID: ");
                int PersonalID = scanner.nextInt();
                System.out.println("Enter Age: ");
                int Age = scanner.nextInt();
                System.out.println("Is The Client Married ? (true/false): ");
                boolean IsMarried = scanner.nextBoolean();
                System.out.println("Enter Address: ");
                String Address = scanner.nextLine();
                System.out.println("Enter Job: ");
                String Job = scanner.nextLine();
                System.out.println("Enter Gender (Male/Female): ");
                String Gender = scanner.nextLine();
                Individual IndieClient = new Individual(FullName, PhoneNumber, Nationality, IsVip, PersonalID, IsMarried,
                        Address, Job, Gender, Age);
                IndieClient.setID(IncrementID(1));
                ArrayList<ArrayList<Client>> ClientArray = InputData(ClientFile);
                ClientArray.get(0).add(IndieClient);
                OutputData(ClientFile, ClientArray);
                System.out.println("Voila!! Individual Profile Created Successfully");
                System.out.println(IndieClient);
                StartApp();
            }
            else if (ClientOption == 2){
                System.out.println("Enter Address: ");
                String Address = scanner.nextLine();
                System.out.println("Enter Discount: ");
                float Discount = scanner.nextFloat();
                System.out.println("Enter Representative Name: ");
                String RepresentativeName = scanner.nextLine();
                System.out.println("Enter Representative Phone: ");
                String RepresentativePhone = scanner.nextLine();
                Agent AgentClient = new Agent(FullName, PhoneNumber, Nationality, IsVip, Address, Discount,
                        RepresentativeName, RepresentativePhone);
                AgentClient.setID(IncrementID(1));
                ArrayList<ArrayList<Client>> ClientArray = InputData(ClientFile);
                ClientArray.get(1).add(AgentClient);
                OutputData(ClientFile, ClientArray);
                System.out.println("Voila!! Agent Profile Created Successfully");
                System.out.println(AgentClient);
                StartApp();
            }
            else if (ClientOption == 3){
                System.out.println("Enter Discount: ");
                float Discount = scanner.nextFloat();
                System.out.println("Enter Representative Name: ");
                String RepresentativeName = scanner.nextLine();
                System.out.println("Enter Representative Phone: ");
                String RepresentativePhone = scanner.nextLine();
                System.out.println("Enter Business Type: ");
                String BusinessType = scanner.nextLine();
                System.out.println("Enter Number Of Members: ");
                int NumberOfMembers = scanner.nextInt();
                SpecialGroup GroupClient = new SpecialGroup(FullName, PhoneNumber, Nationality, IsVip, Discount,
                        RepresentativeName, RepresentativePhone, BusinessType, NumberOfMembers);
                GroupClient.setID(IncrementID(1));
                ArrayList<ArrayList<Client>> ClientArray = InputData(ClientFile);
                ClientArray.get(2).add(GroupClient);
                OutputData(ClientFile, ClientArray);
                System.out.println("Voila!! Special Group Profile Created Successfully");
                System.out.println(GroupClient);
                StartApp();
            }

            else {
                System.out.println("Ah!! Something went Wrong, Please make sure to enter a number from 1-3");
                CreateClient();
            }

        } catch (Exception e){
            System.out.println("Ah!! Something went Wrong, Please make sure to enter a proper values!!");
            String unUsedVar = scanner.next();
            CreateClient();
        }


    }

    public static void CreateStaff() throws IOException, ClassNotFoundException, ParseException {
        try{
            System.out.println("Enter Full Name: ");
            String FullName = scanner.next();
            System.out.println("Enter Age: ");
            int Age = scanner.nextInt();
            System.out.println("Enter Phone Number: ");
            String PhoneNumber = scanner.next();
            System.out.println("Enter Address: ");
            String Address = scanner.next();
            System.out.println("Enter Certification: ");
            String Certification = scanner.next();
            System.out.println("Enter Salary: ");
            Double Salary = scanner.nextDouble();
            System.out.println("Enter Position: ");
            String Position = scanner.next();
            System.out.println("Enter the Department:\n *Kitchen\n *Security\n *RoomService\n *Secretary");
            String Department = scanner.next();
            ArrayList<String> DepartmentOptions = new ArrayList<>(List.of("Kitchen", "Security", "RoomService", "Secretary"));
            if(DepartmentOptions.contains(Department)){
                Staff staffMember = new Staff(FullName, Age, PhoneNumber, Address, Certification, Salary, Position, Department);
                staffMember.setID(IncrementID(2));
                ArrayList<Staff> staffArray = InputData(StaffFile);
                staffArray.add(staffMember);
                OutputData(StaffFile, staffArray);
                System.out.println("Voila!! Staff Member Created Successfully");
                System.out.println(staffMember);
                StartApp();
            }
            else{
                System.out.println("You entered a wrong department option");
                CreateStaff();
            }


        }catch (Exception e){
            System.out.println("Ah!! Something went Wrong, Please make sure to enter a proper values!!");
            String unUsedVar = scanner.next();
            CreateStaff();
        }

    }

    public static double CalculateReceipt(double pricePerDay, String arrivalDate, String departureDate, float discount){
        LocalDate ArrivalDate = LocalDate.parse(arrivalDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate DepartureDate = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Duration diff = Duration.between(ArrivalDate.atStartOfDay(), DepartureDate.atStartOfDay());
        long diffDays = diff.toDays();
        double pricePreDiscount = diffDays * pricePerDay;
        double totalReceipt = pricePreDiscount - (pricePreDiscount * discount);
        return totalReceipt;
    }


    public static void Archive()throws IOException, ClassNotFoundException{
        ArrayList<Reservation> ReservationArray = InputData(ReservationFile);
        ArrayList<Reservation> ArchiveArray = InputData(ArchiveFile);
        ArrayList<ArrayList<Room>> Room = InputData(RoomFile);
        ArrayList<Integer> RoomsID = new ArrayList<>();
        for (Reservation reservation: ReservationArray){
            if (reservation.isAvailable()){
                RoomsID.add(reservation.getRoomID());
                ArchiveArray.add(reservation);
                ReservationArray.remove(reservation);
            }
        for(ArrayList<Room> roomArray: Room){
            for(Room room: roomArray){
                if (RoomsID.contains(room.getRoomID())){
                    room.setStatus("Available");
                }

        OutputData(ReservationFile, ReservationArray);
        OutputData(ArchiveFile, ArchiveArray);
        OutputData(RoomFile, Room);

            }
        }
        }
    }

    public static void EndReservation (int roomID)throws IOException, ClassNotFoundException{
        ArrayList<Reservation> ReservationArray = InputData(ReservationFile);
        ArrayList<Reservation> ArchiveArray = InputData(ArchiveFile);
        ArrayList<ArrayList<Room>> Room = InputData(RoomFile);
        for (Reservation reservation: ReservationArray) {
            if (reservation.getRoomID() == roomID) {
                ArchiveArray.add(reservation);
                for(ArrayList<Room> roomArray: Room) {
                    for (Room room : roomArray) {
                        if (roomID == room.getRoomID()) {
                            room.setStatus("Available");
                        }
                    }
                }
                OutputData(ReservationFile, ReservationArray);
                OutputData(ArchiveFile, ArchiveArray);
                OutputData(RoomFile, Room);
            }
        }



    }

    public static void ResetDataBase() throws IOException, ClassNotFoundException {

        ArrayList<Staff> staffArray = new ArrayList<>();

        ArrayList<ArrayList> roomArray = new ArrayList<>();
        ArrayList<AccommodationRoom> accommodationRoomArray = new ArrayList<>();
        ArrayList<OfficeRoom> officeRoomArray = new ArrayList<>();
        ArrayList<HallRoom> hallRoomArray = new ArrayList<>();
        ArrayList<MeetingRoom> meetingRoomArray = new ArrayList<>();
        roomArray.add(accommodationRoomArray);
        roomArray.add(officeRoomArray);
        roomArray.add(hallRoomArray);
        roomArray.add(meetingRoomArray);

        ArrayList<ArrayList> clientArray = new ArrayList<>();
        ArrayList<Individual> individualsArray = new ArrayList<>();
        ArrayList<Agent> agentArray = new ArrayList<>();
        ArrayList<SpecialGroup> specialGroupArray = new ArrayList<>();
        clientArray.add(individualsArray);
        clientArray.add(agentArray);
        clientArray.add(specialGroupArray);

        ArrayList<Reservation> reservationArray = new ArrayList<>();

        //[RoomID, ClientID, StaffID]
        ArrayList<Integer> sequenceArray = new ArrayList<>(List.of(0, 0, 0));

        ArrayList<Reservation> ArchiveArray = new ArrayList<>();


        OutputData(StaffFile, staffArray);
        OutputData(RoomFile, roomArray);
        OutputData(ClientFile, clientArray);
        OutputData(ReservationFile, reservationArray);
        OutputData(SequenceFile, sequenceArray);
        OutputData(ArchiveFile, ArchiveArray);
    }

}
