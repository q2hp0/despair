package ds;

import java.nio.file.Path;

public class ds {

    public static void main(String[] ar) {

        if (ar.length < 1 || ar.length > 2) {
            System.err.println("despair: nothing makes sense.");
            System.err.println("usage: despair <path> [--commit]");
            System.exit(1);
        }

        boolean cm = ar.length == 2 && ar[1].equals("--commit");

        if (ar.length == 2 && !cm) {
            System.err.println("despair: invalid emotional state.");
            System.exit(1);
        }

        try {
            Path p = gn.ok(Path.of(ar[0]));
            long[] st = er.go(p, false);

            System.out.println();
            System.out.println("despair");
            System.out.println("---------------------------");
            System.out.println("subject: " + p);
            System.out.println("files: " + st[0]);
            System.out.println("folders: " + st[1]);
            System.out.println("---------------------------");

            if (!cm) {
                System.out.println("nothing has happened.");
                System.out.println("add --commit to abandon everything.");
                return;
            }

            long[] rm = er.go(p, true);

            System.out.println("it is over.");
            System.out.println("files removed: " + rm[0]);
            System.out.println("folders removed: " + rm[1]);

        } catch (Exception ex) {
            System.err.println("despair: " + ex.getMessage());
            System.exit(1);
        }
    }
}
