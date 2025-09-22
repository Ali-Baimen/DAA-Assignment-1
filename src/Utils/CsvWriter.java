package Utils;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private FileWriter writer;
    private boolean headerWritten = false;

    public CsvWriter(String filename) throws IOException {
        writer = new FileWriter(filename, true);
    }

    public void writeHeader(String... headers) throws IOException {
        if (!headerWritten) {
            for (int i = 0; i < headers.length; i++) {
                writer.append(headers[i]);
                if (i < headers.length - 1) writer.append(",");
            }
            writer.append("\n");
            headerWritten = true;
        }
    }

    public void writeRow(Object... values) throws IOException {
        for (int i = 0; i < values.length; i++) {
            writer.append(values[i].toString());
            if (i < values.length - 1) writer.append(",");
        }
        writer.append("\n");
    }

    public void close() throws IOException {
        writer.flush();
        writer.close();
    }
}
