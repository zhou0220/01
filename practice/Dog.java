public class Dog {
    public int weightInPounds;
    
    public Dog(int w){
        weightInPounds = w;
    }

    public void makeNoise(){
        if (weightInPounds < 10){
            System.out.println("yip!");
        } else if (weightInPounds < 30){
            System.out.println("Bark!");
        } else{
            System.out.println("wooooooooo!");
        }
        
    }

    public static Dog maxDog(Dog d1, Dog d2) {
        if (d1.weightInPounds > d2.weightInPounds){
            return d1;
        }
        return d2;
    }

    public Dog maxDog1(Dog d2){
        if (this.weightInPounds > d2.weightInPounds){
            return this;
        }
        return d2;
    }
}