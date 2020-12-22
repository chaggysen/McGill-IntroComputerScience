import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException; 


public class CatTree implements Iterable<CatInfo>{
    public CatNode root;
    
    public CatTree(CatInfo c) {
        this.root = new CatNode(c);
    }
    
    private CatTree(CatNode c) {
        this.root = c;
    }
    
    
    public void addCat(CatInfo c)
    {
        this.root = root.addCat(new CatNode(c));
    }
    
    public void removeCat(CatInfo c)
    {
        this.root = root.removeCat(c);
    }
    
    public int mostSenior()
    {
        return root.mostSenior();
    }
    
    public int fluffiest() {
        return root.fluffiest();
    }
    
    public CatInfo fluffiestFromMonth(int month) {
        return root.fluffiestFromMonth(month);
    }
    
    public int hiredFromMonths(int monthMin, int monthMax) {
        return root.hiredFromMonths(monthMin, monthMax);
    }
    
    public int[] costPlanning(int nbMonths) {
        return root.costPlanning(nbMonths);
    }
    
    
    
    public Iterator<CatInfo> iterator()
    {
        return new CatTreeIterator();
    }
    
    
    class CatNode {
        
        CatInfo data;
        CatNode senior;
        CatNode same;
        CatNode junior;
        
        public CatNode(CatInfo data) {
            this.data = data;
            this.senior = null;
            this.same = null;
            this.junior = null;
        }
        
        public String toString() {
            String result = this.data.toString() + "\n";
            if (this.senior != null) {
                result += "more senior " + this.data.toString() + " :\n";
                result += this.senior.toString();
            }
            if (this.same != null) {
                result += "same seniority " + this.data.toString() + " :\n";
                result += this.same.toString();
            }
            if (this.junior != null) {
                result += "more junior " + this.data.toString() + " :\n";
                result += this.junior.toString();
            }
            return result;
        }
        
        
        public CatNode addCat(CatNode c) {
        	
        	CatNode currentCatNode;
        	root = CatNode.this;
        	
        	// Empty Tree
        	if (c == null) {
        		return root;
        	}
        	
        	if (root == null) {
        		root = makeCatNode (c.data);
        	}
        	
        	// If not empty, descent to the leaf node according to the input data
        	
        	else {
        		currentCatNode = root;
        		while(true) {
        			
        			// New data < data at node, branch senior
        			
        			if (c.data.monthHired < currentCatNode.data.monthHired) {
        				if (currentCatNode.senior == null) {
        					currentCatNode.senior = makeCatNode(c.data);
        					break;
        				}else {
        					currentCatNode = currentCatNode.senior;
        				}
        			
        			// New data > data at node, branch junior	
        				
        			} else if (c.data.monthHired > currentCatNode.data.monthHired) {
        				if (currentCatNode.junior == null) {
        					currentCatNode.junior = makeCatNode(c.data);
        					break;
        				}else {
        					currentCatNode = currentCatNode.junior;
        				}
        				
        			// New data = data at node, branch same	
        				
        			} else {
        				if (currentCatNode.same == null && currentCatNode.data.furThickness > c.data.furThickness) {
        					currentCatNode.same = makeCatNode(c.data);
        					break;
        				} else if (currentCatNode.same == null && currentCatNode.data.furThickness < c.data.furThickness) {
        					CatNode copy = null;
        					copy = currentCatNode;
        					copy.junior = currentCatNode.junior;
        					copy.same = currentCatNode.same;
        					copy.senior = currentCatNode.senior;
        					currentCatNode = c;
        					c.same = copy;
        					c.senior = copy.senior;
        					c.junior = copy.junior;
        					root = currentCatNode;
        					break;
        				} else if (currentCatNode.same != null && currentCatNode.same.data.furThickness > c.data.furThickness){
        					currentCatNode = currentCatNode.same;
        				} else if (currentCatNode.same != null && currentCatNode.same.data.furThickness < c.data.furThickness){
        					CatNode copy = null;
        					copy = currentCatNode;
        					copy.junior = currentCatNode.junior;
        					copy.same = currentCatNode.same;
        					copy.senior = currentCatNode.senior;
        					currentCatNode = c;
        					c.same = makeCatNode(copy.data);
        					c.same.same = copy.same;
        					c.senior = copy.senior;
        					c.junior = copy.junior;
        					root = currentCatNode;
        					break;
        					//currentCatNode = currentCatNode.same;
        					/*CatNode copy = null;
        					copy = currentCatNode.same;
        					copy.junior = currentCatNode.same.junior;
        					copy.same = currentCatNode.same.same;
        					copy.senior = currentCatNode.same.senior;
        					currentCatNode.same = c;
        					c.same = copy;
        					root = currentCatNode;*/
        				} else if (currentCatNode.same == null && currentCatNode.data.furThickness == c.data.furThickness) {
        					currentCatNode.same = makeCatNode(c.data);
        					break;
        				}
        			}
        		}
        	}
        	return root;
        }
        
        private CatNode makeCatNode (CatInfo data) {
        	CatNode node = new CatNode(data);
        	node.senior = null;
        	node.junior = null;
        	node.same = null;
        	return node;
        }
        
        
        public CatNode removeCat(CatInfo c) {
            
        	CatNode currentCatNode;
        
        	root = CatNode.this;
        	
        	currentCatNode = root;
        	
        	if (root == null) return root;
        	
        	/*else {
        		currentCatNode = root;
        		if(currentCatNode.data == c) {
        			if (currentCatNode.same != null) {
        				CatNode copy = null;
        			    copy = currentCatNode;
        			    currentCatNode = copy.same;
        			    currentCatNode.senior = copy.senior;
        			    currentCatNode.junior = copy.junior;
        			}
        			else if (currentCatNode.same == null && currentCatNode.senior != null) {
        				CatNode copy= null;
        				copy = currentCatNode;
        				currentCatNode = copy.senior;
        				currentCatNode.addCat(copy.junior);
        			}
        			else if (currentCatNode.same == null && currentCatNode.senior == null) {
        				currentCatNode = currentCatNode.junior;
        			}
        		}
        	}*/
        	while (true) {
        	    if (currentCatNode.data == c) {
        		
        		if (currentCatNode.same != null) {
        			CatNode copy = null;
        			copy = currentCatNode;
        			copy.junior = currentCatNode.junior;
					copy.same = currentCatNode.same;
					copy.senior = currentCatNode.senior;
        			currentCatNode = copy.same;
        			currentCatNode.senior = copy.senior;
        			currentCatNode.junior = copy.junior;
        			root.junior = currentCatNode;
        			break;
        		}
        		else if(currentCatNode.same == null && currentCatNode.senior != null) {
        			currentCatNode = this.senior;
        			root = currentCatNode;
        			currentCatNode.addCat(this.junior);
        			break;
        		}
        		else if (currentCatNode.same == null && currentCatNode.senior == null) {
        			currentCatNode = this.junior;
        			break;
        		}
        		
        	    }/*else if (currentCatNode.junior != null || currentCatNode.same != null || currentCatNode.senior != null){
        		if (currentCatNode.junior != null) currentCatNode = currentCatNode.junior;
        		if (currentCatNode.same != null) currentCatNode = currentCatNode.same;
        		if (currentCatNode.senior != null) currentCatNode = currentCatNode.senior;
        		
        		
        	  }*/
        	    else if (currentCatNode.junior != null) currentCatNode = currentCatNode.junior;
        	    else if (currentCatNode.same != null) currentCatNode = currentCatNode.same;
        	    else if (currentCatNode.senior != null) currentCatNode = currentCatNode.senior;
        	    else {
        		  break;
        	  }
        	}
        	
        	return root;
        	
        }
        
        
        public int mostSenior() {
        	
        	if (this.senior == null && this.same != null) this.same.mostSenior();
        	else if(this.senior != null) this.senior.mostSenior();
        	return this.data.monthHired;
           
        }
        
        public int fluffiest() {
            int currentFluffiest = 0;
            if (currentFluffiest < this.data.furThickness) currentFluffiest = this.data.furThickness;
            if (this.senior != null) this.senior.fluffiest();
            if (this.same != null) this.same.fluffiest();
            if (this.junior != null) this.junior.fluffiest();
            return currentFluffiest;
        }
        
        
        public int hiredFromMonths(int monthMin, int monthMax) {
        	
        	int count = 0;
        	
        	if (monthMin > monthMax) return count;
        	if (this.data.monthHired >= monthMin && this.data.monthHired <= monthMax) count += 1;
        	if (this.senior != null) count += this.senior.hiredFromMonths(monthMin, monthMax);
        	if (this.same != null) count += this.same.hiredFromMonths(monthMin, monthMax);
        	if (this.junior != null) count+= this.junior.hiredFromMonths(monthMin, monthMax);
        	
        	return count;
        }
        
        public CatInfo fluffiestFromMonth(int month) {
        int maxFurThickness = 0;
        CatInfo currentCatInfo = null;
           if (this.data.monthHired == month) {
        	   if (maxFurThickness < this.data.furThickness) {
        		   maxFurThickness = this.data.furThickness;
        		   currentCatInfo = this.data;
        	   }
           }
           if (this.senior != null) this.senior.fluffiestFromMonth(month);
           if (this.same != null) this.same.fluffiestFromMonth(month);
           if (this.junior != null) this.junior.fluffiestFromMonth(month);
           
           return currentCatInfo;
        }
        
        public int[] costPlanning(int nbMonths) {
        	int CURRENT_MONTH = 243;
            int i = nbMonths + CURRENT_MONTH;
            int [] costPlanningArray = new int [nbMonths];
            for (int j = 0; j < nbMonths; j++) {
            	if (this.data.monthHired == j + CURRENT_MONTH) {
            		costPlanningArray[j] += this.data.furThickness;
            	}
            	if (this.senior != null) this.senior.costPlanning(nbMonths);
            	if (this.same != null) this.same.costPlanning(nbMonths);
            	if (this.junior != null) this.junior.costPlanning(nbMonths);
            }
            
            return costPlanningArray;
        }
        
    }
    
    private class CatTreeIterator implements Iterator<CatInfo> {
        ArrayList <CatInfo> catInfoArrayList = new ArrayList<CatInfo>();
        
        public CatTreeIterator() {
           inorderTreeTraversal(root);
        }
        
        private ArrayList inorderTreeTraversal(CatNode root) {
			if (root != null) {
				inorderTreeTraversal(root.senior);
				catInfoArrayList.add(root.data);
				inorderTreeTraversal(root.same);
				inorderTreeTraversal(root.junior);
			}
			
			return catInfoArrayList;
        }
        
        public CatInfo next(){
            //YOUR CODE GOES HERE
            return null; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
        }
        
        public boolean hasNext() {
            //YOUR CODE GOES HERE
            return false; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
        }
    }
    
}

