classdef DataflowTest < matlab.unittest.TestCase
    
    methods(Test)
        function testGraphDotString(testcase)
            g = createLinearGraph(4);
            s = g.toDotString;
            s = char(s);
            testcase.verifyEqual(isempty(s), false);
        end
    
        function testForward1(testcase)
            g = createLinearGraph(4);
            r = my.se.Reachable(g);
            inactive = my.se.Inactive([4 3], []);
            b = r.forward(1, inactive);
            testcase.verifyEqual(length(b), 2);
        end
        function testForward2(testcase)
            g = createLinearGraph(5);
            r = my.se.Reachable(g);
            inactive = my.se.Inactive([], 2);
            b = r.forward(1, inactive);
            testcase.verifyEqual(length(b), 3);
        end
        function testBackward(testcase)
            g = createLinearGraph(5);
            r = my.se.Reachable(g);
            inactive = my.se.Inactive([], 2);
            b = r.backward(5, inactive);
            testcase.verifyEqual(length(b), 2);
        end
        
        function testTreeDotString(testcase)
            t = createSampleTree;
            s = t.toDotString;
            s = char(s);
            testcase.verifyEqual(isempty(s), false);
        end
        function testTreeGetNode(testcase)
            t = createSampleTree;
            foundB = t.getNode(2);
            testcase.verifyEqual(foundB.id, 2);
            
            try
                b = t.getNode(5);
            catch e %
                b = [];
            end
            testcase.verifyEqual(isempty(b), true);
        end
        
        function testLabelFwd(testcase)
            g= createTestGraph();
            Op = my.se.IntLabelOperation;
            Lbl = my.se.IntLabel(g, Op);
            labels = Lbl.emptyLabel;
            Lbl.setLabel(labels, 1,1);
            Lbl.setLabel(labels, 2,2);
            Lbl.forward([1;2], labels);
            
            l5 = Lbl.getLabel(labels, 5);
            testcase.verifyEqual(any(l5==1), true);
            testcase.verifyEqual(any(l5==2), true);
        end
        
        function testLabelBwd(testcase)
            g= createTestGraph();
            Op = my.se.IntLabelOperation;
            Lbl = my.se.IntLabel(g, Op);
            labels = Lbl.emptyLabel;
            Lbl.setLabel(labels, 5,5);
            Lbl.setLabel(labels, 3,3);
            Lbl.backward(5, labels);
            
            l1 = Lbl.getLabel(labels, 1);
            testcase.verifyEqual(any(l1==3), true);
            testcase.verifyEqual(any(l1==5), true);  
        end
        
        function testLabelBoth(testcase)
            g= createLinearGraph(5);
            Op = my.se.IntLabelOperation;
            Lbl = my.se.IntLabel(g, Op);
            labels = Lbl.emptyLabel;
            Lbl.setLabel(labels, 2,2);
            Lbl.setLabel(labels, 4,4);
            Lbl.backward([2;4;5], labels);
            Lbl.forward([1;2;4], labels);
            
            l1 = Lbl.getLabel(labels, 3);
            testcase.verifyEqual(any(l1==2), true);
            testcase.verifyEqual(any(l1==4), true);
        end
        
        function testLabelLoop(testcase)
            for i=1:5
                v(i) = my.se.Vertex(i, mat2str(i)); %#ok<AGROW>
            end
            f = my.se.GraphFactory();
            g = f.make(v);
            g.addEdge(1,2);
            g.addEdge(2,3);
            g.addEdge(3,4);
            g.addEdge(4,5);
            g.addEdge(5,2);
            
            Op = my.se.IntLabelOperation;
            Lbl = my.se.IntLabel(g, Op);
            labels = Lbl.emptyLabel;
            Lbl.setLabel(labels, 2,2);
            Lbl.setLabel(labels, 1,1);
            Lbl.backward([1;2], labels);
            
            l3 = Lbl.getLabel(labels, 3);
            testcase.verifyEqual(any(l3==2), true);
            testcase.verifyEqual(any(l3==1), false);
            l1 = Lbl.getLabel(labels, 1);
            testcase.verifyEqual(any(l1==2), true);
        end
        
        function testLabelPair(testcase)
            g = amTestGraph();
            Op = my.se.IntLabelOperation;
            Lbl = my.se.IntLabel(g, Op);
            ilabels = Lbl.emptyLabel;
            olabels = Lbl.emptyLabel;
            % Input label
            Lbl.setLabel(ilabels, 1,1);
            Lbl.setLabel(ilabels, 4,2);
            Lbl.setLabel(ilabels, 7,3);
            Lbl.forward([1;4;7], ilabels);
            
            % Output label
            Lbl.setLabel(olabels, 3,1);
            Lbl.setLabel(olabels, 6,2);
            Lbl.setLabel(olabels, 9,3);
            
            Lbl.backward([3;6;9], olabels);
            
            b = {Lbl.getLabel(ilabels, 2), Lbl.getLabel(olabels,2)};
            testcase.verifyEqual(b{1}, int32(1));
            testcase.verifyEqual(length(b{2}), 3);
            e = {Lbl.getLabel(ilabels, 5), Lbl.getLabel(olabels,5)};
            testcase.verifyEqual(length(e{1}), 2);
            testcase.verifyEqual(length(e{2}), 2);
        end
    end
    
    methods
       
    end
end

function g= createTestGraph()
import my.se.Graph
g = Graph;
for i=1:5
    v(i) = g.newVertex(mat2str(i)); %#ok<AGROW>
end
g.addEdge(1,3);
g.addEdge(2,3);
g.addEdge(1,4);
g.addEdge(4,5);
g.addEdge(3,5);
end

function g = amTestGraph()
% Test graph from Alongkrit and Mohamed
import my.se.Graph
g = Graph;
for i=1:9
    v(i) = g.newVertex(mat2str(i)); %#ok<AGROW>
end
g.addEdge(1,2);
g.addEdge(2,3);
g.addEdge(2,5);
g.addEdge(4,5);
g.addEdge(5,6);
g.addEdge(7,8);
g.addEdge(8,9);
g.addEdge(5,9);
end

function g = createLinearGraph(n)
% Create a linear graph 1->2->3...->i
import my.se.Graph
g = Graph;
for i=1:n
    v(i) = g.newVertex(mat2str(i)); %#ok<AGROW>
end
for i=1:n-1
    g.addEdge(i, i+1);
end
end

function a =createSampleTree()
f = my.se.TreeNodeFactory;
a = f.make();
b = f.make();
c = f.make();
d = f.make();
a.addChild(b);
a.addChild(c);
b.addChild(d);
end