% Demo new graph
a = {'ain'};
b = {'b'};
c = {'A'};
n = 100000;
A = repmat(a, 1, n);
B = repmat(b, 1, n);
C = repmat(c, 1, n);

g = sl.ir.DataflowGraph;
vars = g.newVarNodes(A);
inputs = g.newInputNodes(B);
procs = g.newProcNodes(C);

g.addEdges(inputs, procs);
g.addEdges(procs, vars);