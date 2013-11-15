function g = createLinGraph3(n)
% Create a simple linear graph with node type and edges.
totalV = 0;  %
vTypes = [];
totalE = 0;
edges =  zeros(10,2);

inputs = [];
outputs = [];
m = containers.Map('KeyType', 'double', 'ValueType', 'double');

for i=1:n
    [totalV, vTypes] = createInput(totalV, vTypes);
    m(totalV) = totalV;
    vIn = totalV;
    [totalV, vTypes] = createProc(totalV, vTypes);
    proc = totalV;
    m(totalV) = totalV;
    [totalE, edges] = addEdge(totalE, edges, vIn, proc);
    [totalV, vTypes] = createVar(totalV, vTypes);
    m(totalV) = totalV;
    [totalE, edges] = addEdge(totalE, edges, proc, totalV);
    
   inputs(end+1) = vIn; %#ok<AGROW> 
   outputs(end+1) = totalV; %#ok<AGROW>
end

for i=1:n-1
    [totalE, edges] = addEdge(totalE, edges, outputs(i), inputs(i+1));
end


g = sl.ir.DataflowGraph;
g.createNodesFromArray(vTypes(1:totalV));
g.addEdgesFromArray(edges(1:totalE,1), edges(1:totalE,2), ones(totalE,1));
end


function [totalV, vTypes] = createInput(totalV, vTypes)
totalV = totalV + 1;
if totalV > size(vTypes,1)
    vTypes = [vTypes; zeros(size(vTypes))];
end

vTypes(totalV) = 3;

end

function [totalV, vTypes] = createProc(totalV, vTypes)
totalV = totalV + 1;
if totalV > size(vTypes,1)
    vTypes = [vTypes; zeros(size(vTypes))];
end
vTypes(totalV) = 2;
end

function [totalV, vTypes] = createVar(totalV, vTypes)
totalV = totalV + 1;
if totalV > size(vTypes,1)
    vTypes = [vTypes; zeros(size(vTypes))];
end
vTypes(totalV) = 1;
end

function [totalE, edges] = addEdge(totalE, edges, s, d)
totalE = totalE + 1;
if totalE > size(edges,1)
    edges = [edges; zeros(size(edges))];
end
edges(totalE,1) =  s;
edges(totalE,2) = d;
end