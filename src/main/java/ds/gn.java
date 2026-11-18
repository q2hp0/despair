package ds;

import java.io.IOException;
import java.nio.file.*;

public class gn {

    static final String[] no = {
        "/etc", "/usr", "/var", "/boot",
        "/proc", "/sys", "/dev", "/bin",
        "/sbin", "/lib", "/lib64", "/root"
    };

    public static Path ok(Path p) throws IOException {

        p = p.toAbsolutePath().normalize();

        if (Files.isSymbolicLink(p)) {
            throw new IOException("symbolic targets are not welcome.");
        }

        if (!Files.isDirectory(p, LinkOption.NOFOLLOW_LINKS)) {
            throw new IOException("the subject does not exist.");
        }

        p = p.toRealPath();

        Path hm = Path.of(System.getProperty("user.home")).toRealPath();
        Path wd = Path.of("").toRealPath();

        if (p.equals(p.getRoot())
                || p.equals(hm)
                || hm.startsWith(p)
                || wd.startsWith(p)) {
            throw new IOException("this place must remain.");
        }

        for (String s : no) {
            Path q = Path.of(s);

            if (Files.exists(q)) {
                q = q.toRealPath();

                if (p.startsWith(q) || q.startsWith(p)) {
                    throw new IOException("protected territory.");
                }
            }
        }

        return p;
    }
}
