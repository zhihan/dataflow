% Demo new graph
n = 30000

fprintf('Optimized\n');
tic,
g1 = createLinGraph(n);
toc

fprintf('Un-optimized\n');
tic
g2 = createLinGraph0(n);
toc