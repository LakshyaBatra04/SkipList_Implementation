public class SkipList {

        public SkipListNode head;
        public SkipListNode tail;
        public int height;
        public Randomizer randomizer;
        private final int NEG_INF = Integer.MIN_VALUE;
        private final int POS_INF = Integer.MAX_VALUE;

        SkipList(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            this.head = new SkipListNode(NEG_INF,1);
            this.tail = new SkipListNode(POS_INF,1);
            this.head.next.add(0,this.tail);
            this.tail.next.add(0,null);
            this.height = 1;
            this.randomizer = new Randomizer();
        }
        private void deleteFromHeight(SkipListNode node){
            int node_height=node.height;
            while(node_height>0){
                SkipListNode temp=head.next.get(node_height-1);
                if(temp.value==node.value){
                    SkipListNode temp2=temp.next.get(node_height-1);
                    head.next.set(node_height-1,temp2);
                    if(head.next.get(node_height-1)==tail){
                        height--;
                        head.height--;
                        tail.height--;
                    }
                    node_height--;
                }
                else{
                    while(temp.next.get(node_height-1).value!=node.value){
                        temp=temp.next.get(node_height-1);
                    }
                    SkipListNode temp2=temp.next.get(node_height-1).next.get(node_height-1);
                    temp.next.set(node_height-1,temp2);
                    if(head.next.get(node_height-1)==tail){
                        height--;
                        head.height--;
                        tail.height--;
                    }
                    node_height--;
                }
            }
            

        }
        public boolean delete(int num){
            // TO be completed by students
            if(search(num)==false){
                return false;
            }  
            SkipListNode pointer=head;
            int curr_height=this.height;

            while(pointer.value!=num){
                if(pointer.next.get(curr_height-1).value>num){
                    curr_height--;
                }
                else{
                    pointer=pointer.next.get(curr_height-1);
                }

            }
            curr_height=pointer.height;
            deleteFromHeight(pointer);
            return true;
        }

        public boolean search(int num){
            // DO NOT TOUCH, BITCH !
            int curr_height=this.height;
            SkipListNode pointer=head;
            int operations=0;

            while(curr_height>0&&pointer.value!=num){
                if(pointer.next.get(curr_height-1).value>num){
                    curr_height--;
                    operations++;

                    continue;
                }
                else{
                    operations++;

                    pointer=pointer.next.get(curr_height-1);
                }
            }
            System.out.println("Number of operations " +operations);
            if(pointer.value==num){
                return true;
            }    
            return false;
        }

        public Integer upperBound(int num){ 
            // DO NOT TOUCH, BITCH.
            int curr_height=this.height;
            SkipListNode pointer=head;
            int operations=0;

            while(curr_height>0){
                
                if(pointer.next.get(curr_height-1).value>num){
                    curr_height--;
                    operations++;
                    continue;
                }
                else{
                    operations++;
                    pointer=pointer.next.get(curr_height-1);
                }
            }
            System.out.println("Number of operations "+operations);
            return pointer.next.get(0).value;        
        }
        //WORKING ASOLUTELY FINE . DO NOT TOUCH, BITCH.
        public void insertAtHeight(SkipListNode node,int height){

            for(int i=0;i<height;i++){
                SkipListNode temp=head.next.get(i);
                
                if(temp.value==POS_INF){
                    SkipListNode store=temp;
                    head.next.set(i,node);
                    node.next.add(i,store);
                    continue;
                }
                else{
                    SkipListNode curr=temp;
                    if(curr.value>node.value){
                        this.head.next.set(i,node);
                        node.next.add(i,curr);
                        continue;
                    }
                    while(curr.next.get(i).value<node.value){
                        curr=curr.next.get(i);
                    }
                    SkipListNode temp2=curr.next.get(i);
                    curr.next.set(i,node);
                    node.next.add(i,temp2);
                }
            }      
        }
        public void insert(int num){
            // TO be completed by students
            SkipListNode nodeToBeInserted=new SkipListNode(num, 1);
            int nodeHeight=1;

            while(randomizer.binaryRandomGen()){
                nodeHeight++;
                nodeToBeInserted.height=nodeHeight;
                if(nodeHeight>this.height){
                    this.height=nodeHeight;
                    head.height=nodeHeight;
                    tail.height=nodeHeight;
                    this.head.next.add(nodeHeight-1,this.tail);
                    this.tail.next.add(nodeHeight-1,null);      
                }
            }
            
           insertAtHeight(nodeToBeInserted,nodeHeight);      
        }

        public void print(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            for(int i = this.height ; i>=1; --i){
                SkipListNode it = this.head;
                while(it!=null){
                    if(it.height >= i){
                        System.out.print(it.value + "\t");
                    }
                    else{
                        System.out.print("\t");
                    }
                    it = it.next.get(0);
                }
                System.out.println("null");
            }
        }
        public static void main(String[] args) {
            SkipList s=new SkipList();
            for(int i=0;i<11;i++){
                s.insert(5*i);
            }
            
            s.print();
            System.out.println(s.search(5));
            System.out.println(s.search(30));
            System.out.println(s.search(31));
            System.out.println(s.search(51));
            System.out.println(s.search(-1)); 
            System.out.println(s.upperBound(5));
            System.out.println(s.upperBound(26));
            System.out.println(s.upperBound(51));
            System.out.println(s.upperBound(31)); 
            s.delete(5);
            s.delete(10);
            s.delete(15);
            s.print(); 
            SkipList skiplistobj = new SkipList();
            skiplistobj.insert(3);
            skiplistobj.insert(1);
            skiplistobj.insert(5);
            skiplistobj.insert(8);
            skiplistobj.search(1);
            skiplistobj.search(10);
            skiplistobj.delete(5);
            skiplistobj.insert(4);
            skiplistobj.search(5);
            skiplistobj.search(4);
            skiplistobj.upperBound(3);
            skiplistobj.print();
        }
}