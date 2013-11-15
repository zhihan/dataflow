function g = createLinGraph0(n)
% Create a simple linear graph with node type and edges.
g = sl.ir.DataflowGraph;
inputs = [];
outputs = [];
for i=1:n
    vIn = g.newInputNode('');
    p = g.newProcNode('');
    g.addEdge(vIn.id, p.id);
    vOut = g.newVarNode('');
    g.addEdge(p.id, vOut.id);
    
   inputs(end+1) = vIn.id; %#ok<AGROW> 
   outputs(end+1) = vOut.id; %#ok<AGROW>
end

for i=1:n-1
    g.addEdge(outputs(i), inputs(i+1));
end

end


