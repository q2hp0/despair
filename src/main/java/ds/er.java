package ds;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class er {

    public static long[] go(Path p, boolean cm) throws IOException {

        long[] st = new long[2];

        Files.walkFileTree(p, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(
                    Path f, BasicFileAttributes a) throws IOException {

                st[0]++;

                if (cm) {
                    Files.delete(f);
                }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(
                    Path f, IOException ex) throws IOException {

                throw ex;
            }

            @Override
            public FileVisitResult postVisitDirectory(
                    Path d, IOException ex) throws IOException {

                if (ex != null) throw ex;

                if (!d.equals(p)) {
                    st[1]++;

                    if (cm) {
                        Files.delete(d);
                    }
                }

                return FileVisitResult.CONTINUE;
            }
        });

        return st;
    }
}
