package cl.degu.sparql;

import org.apache.jena.sys.JenaSystem;
import org.apache.jena.query.Query;
import org.apache.jena.sparql.algebra.OpAsQuery;
import org.apache.jena.sparql.algebra.Algebra;

/**
 * Show how different S-Expressions are translated to SPARQL
 * by the Jena library.
 */
public class App 
{
    public static Query translate(String sse) {
        return OpAsQuery.asQuery(Algebra.parse(sse));
    }

    public static void main( String[] args )
    {
        JenaSystem.init();

        // Example 1
        //
        // SELECT  ?s
        // WHERE
        // { ?s  ?p  ?c }
        String sse1 = "(project ?s (bgp (triple ?s ?p ?c)))";
        System.out.println("Example 1");
        System.out.println(sse1 + "\n\n" + translate(sse1) + "\n");

        // Example 2
        //
        // SELECT  ?s
        // WHERE
        // { ?s  <http://example.org>  ?c }
        String sse2 = "(project ?s (bgp (triple ?s <http://example.org> ?c)))";
        System.out.println("Example 2");
        System.out.println(sse2 + "\n\n" + translate(sse2) + "\n");

        // Example 3
        //
        // SELECT  *
        // WHERE
        // { ?s  ?p  ?o
        //   BIND(<< ?s ?p ?o >> AS ?t)
        // }
        String sse3 = "(extend ((?t << ?s ?p ?o >>)) (bgp (triple ?s ?p ?o)))";
        System.out.println("Example 3");
        System.out.println(sse3 + "\n\n" + translate(sse3) + "\n");

        // Example 4
        //
        // SELECT  *
        // WHERE
        // { ?s  ?p  ?o
        //   BIND(triple(?s, ?p, ?o) AS ?t)
        // }
        String sse4 = "(extend ((?t (triple ?s ?p ?o))) (bgp (triple ?s ?p ?o)))";
        System.out.println("Example 4");
        System.out.println(sse4 + "\n\n" + translate(sse4) + "\n");
    }
}
