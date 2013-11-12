a = {'a'};
n = 10000
b = repmat(a, 1,n);

fprintf('Scalar\n');
g1 = sl.ir.DataflowGraph;
tic,
for i=1:length(b)
    v = g1.newVarNode(b{i});
end
toc,

fprintf('Vectorized\n')
g2 = sl.ir.DataflowGraph;
tic,
v2 = g2.newVarNodes(b);
toc