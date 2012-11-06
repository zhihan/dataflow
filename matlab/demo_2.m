% Simple tree
f = TreeNodeFactory;
a = f.make(1,'1');
b = f.make(2,'2');
c = f.make(3,'3');
d = f.make(4,'4');
a.addChild(b);
a.addChild(c);
b.addChild(d);

a.toDotString

foundB = a.getNode(2);
foundB.id

try
    a.getNode(5)
catch e
    disp(e)
end