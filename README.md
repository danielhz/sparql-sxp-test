# sparql-sxp-test

This is a simple test to see how Jena parse S-Expressions, and translate them to SPARQL.

Executing this code produces the following output:

```
Example 1
(project ?s (bgp (triple ?s ?p ?c)))

SELECT  ?s
WHERE
  { ?s  ?p  ?c }


Example 2
(project ?s (bgp (triple ?s <http://example.org> ?c)))

SELECT  ?s
WHERE
  { ?s  <http://example.org>  ?c }


Example 3
(extend ((?t << ?s ?p ?o >>)) (bgp (triple ?s ?p ?o)))

SELECT  *
WHERE
  { ?s  ?p  ?o
    BIND(<< ?s ?p ?o >> AS ?t)
  }


Example 4
(extend ((?t (triple ?s ?p ?o))) (bgp (triple ?s ?p ?o)))

SELECT  *
WHERE
  { ?s  ?p  ?o
    BIND(triple(?s, ?p, ?o) AS ?t)
  }
```
