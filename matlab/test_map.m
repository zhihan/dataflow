n = 80000;

a = 1:n;
b = 1:n;

fprintf('One shot\n');
tic
m = containers.Map(a,b);
toc

fprintf('Loopd\n');
m2 = containers.Map('KeyType','double', 'ValueType', 'double');
tic
for i=1:n
    m2(a(i)) = b(i);
end
toc



