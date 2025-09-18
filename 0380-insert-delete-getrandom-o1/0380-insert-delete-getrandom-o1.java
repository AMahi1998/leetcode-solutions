class RandomizedSet {
    private final List<Integer> list;
    private final Map<Integer, Integer> pos;//key==> val,value==> index. <val, index>
    private final Random rand;

    public RandomizedSet() {
        list = new ArrayList<>();
        pos = new HashMap<>();
        rand = new Random();
    }
    
    public boolean insert(int val) {
        if(pos.containsKey(val)){
            return false;
        }
        pos.put(val,list.size());
        list.add(val);
        return true;

    }
    
    public boolean remove(int val) {
        Integer i= pos.get(val);
        if(i==null)
        {
            return false;
        }
        int lastIndx=list.size()-1;
        int lastVal= list.get(lastIndx);

        //move last to i  
        list.set(i, lastVal);  
        pos.put(lastVal, i);

        pos.remove(val);
        list.remove(lastIndx);
        return true;
        
    }
    
    public int getRandom() {
        int i=rand.nextInt(list.size());

        return list.get(i);
        
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */