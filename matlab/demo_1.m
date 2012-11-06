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

g.toDotString

%
r = Reachable(g);
inactive = Inactive([4 3], []);
b = r.forward(1, inactive);
b(1).id

inactive = Inactive([4 3], []);
b = r.backward(1, inactive);
b

b = r.backward(3, Inactive([1], []));
b

inactive = Inactive([], [1]);
b = r.backward(4, inactive)
b
