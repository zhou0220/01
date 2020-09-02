public class DogLauncher {
    public static void main(String[] args){
        Dog d = new Dog(51);
        Dog d2 = new Dog(100);
        
        d.makeNoise();
        Dog bigger = d.maxDog1(d2);

    }
}