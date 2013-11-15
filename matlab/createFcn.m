function g = createFcn(n)
total = 0;
for i=1:n
    total = addNew(total);
end
g = sl.ir.DataflowGraph;
v = finalize(g, total);
end

function n = addNew(n)
n = n +1;
end

function edges = addEdges(edges, src, dst)
edges = [edges; src, dst];
end

function objs = finalize(g, n)
x = {'x'};
y = repmat(x, n, 1);
objs = g.newVarNodes(y);
end