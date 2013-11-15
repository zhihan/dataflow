% Demo new graph
n = 20000

fprintf('Optimized\n');
tic,
g1 = createLinGraph(n);
toc

fprintf('Optimized and inlined\n');
tic,
g1 = createLinGraph3(n);
toc

fprintf('Un-optimized\n');
tic
g2 = createLinGraph0(n);
toc