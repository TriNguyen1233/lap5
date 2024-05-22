package builder.pattern1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class NormalDocument extends Document {
    public NormalDocument(String name, String content) {
        this.name = name;
        if (content != null) {
            this.content = content;
        } else {
            throw new IllegalArgumentException("Content cannot be null");
        }
        this.fileType = ".txt";
        this.isEncrypted = false;
    }

    @Override
    public void save() {
        try {
            // Save as .txt file
            String fileName = name + fileType;
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            System.out.println("Saved as .txt file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
