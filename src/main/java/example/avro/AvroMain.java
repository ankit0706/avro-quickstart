package example.avro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class AvroMain {
    
    public static final String AVRO_FILE_NAME = "users.avro";
    
    public static void main(String[] args) throws IOException {

        // Avro object creation method 1 using setters
        User user1 = new User();
        user1.setName("Surya");
        user1.setFavoriteNumber(118);
        user1.setFavoriteColor("Blue");

        // creation method 2 using constructor
        User user2 = new User("Ishan", 68, "Navy");

        // creation method 3 using builder
        User user3 = User.newBuilder()
            .setName("Prithvi")
            .setFavoriteNumber(127)// optional field
            .setFavoriteColor("Red")// optional field
            .build();

        // serialization
        DatumWriter<User> userDatumwriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> userFileWriter = new DataFileWriter<>(userDatumwriter);
        /*
         * We can give path as well to define where the avro file will be written
         * If only name is given then the file is written at the root directory which
         * is the project folder
         */
        File avroFile = new File(AVRO_FILE_NAME);
        try {
            userFileWriter.create(user1.getSchema(), avroFile);
            userFileWriter.append(user1);
            userFileWriter.append(user2);
            userFileWriter.append(user3);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (userFileWriter != null)
                userFileWriter.close();
        }

        // deserialization
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> userFileReader = new DataFileReader<User>(avroFile, userDatumReader);
        while (userFileReader.hasNext()) {
            System.out.println(userFileReader.next());
        }
        userFileReader.close();
    }

}
