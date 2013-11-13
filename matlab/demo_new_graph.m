% Demo new graph
a = {'ain'};
b = {'b'};
c = {'A'};
n = 30000;
A = repmat(a, 1, n);
B = repmat(b, 1, n);
C = repmat(c, 1, n);

tic
g = sl.ir.DataflowGraph;
vars = g.newVarNodes(A);
inputs = g.newInputNodes(B);
procs = g.newProcNodes(C);
toc,

tic,
g.addEdges(inputs, procs);
g.addEdges(procs, vars);
toc