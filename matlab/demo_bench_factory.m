%demo_bench_factory
a = {'a'};
n = 100000
b = repmat(a, 1,n);

fprintf('Scalar\n');
g1 = sl.ir.DataflowGraph;
tic,
for i=1:length(b)
    v = g1.newVarNode(b{i});
end
toc,
%%
fprintf('Delayed creation inlined\n')
tic,
types = [];
total = 0;
for i=1:n
    total = total+1;
    types(i) = 1;
end
x = {'x'};
y = repmat(x, total, 1);
g3 = sl.ir.DataflowGraph;
objs = g3.newVarNodes(y);
toc,

%%
fprintf('Delayed and Vectorized\n')
tic,
g2 = sl.ir.DataflowGraph;
f  = DelayFactory(g2);
for i=1:n
    id = f.create(1);
end
v2 = f.finalize;
toc