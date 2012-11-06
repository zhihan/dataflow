% Try a simple example
v1 = Vertex(1,'1');
v2 = Vertex(2,'2');
v3 = Vertex(3,'3');
v4 = Vertex(4,'4');
V = [v1;v2;v3;v4];
f = GraphFactory();
g = f.make(V);
g.addEdge(v1,v2);
g.addEdge(v2,v3);
g.addEdge(v3,v4);

g.print()

%
r = Reachable(g);
b = r.forward(v1, [4 3]);
b(1).sid

b = r.backward(v1, [4 3]);
b(1).sid

b = r.backward(v3, [1]);
b(1).sid
b(2).sid
