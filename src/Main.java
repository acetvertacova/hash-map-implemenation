public class Main {
    public static void main(String[] args) {

        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("apple", 1);
        map.put("orange", 5);
        map.put("apple", 3);
        map.put("grape", 4);

        System.out.println("============Checking get method================");
        System.out.println(map.get("apple"));
        System.out.println(map.get("grape"));

        System.out.println("============Checking remove method=============");
        System.out.println(map.remove("apple"));

        System.out.println("============Checking containsKey method========");
        System.out.println(map.containsKey("orange"));

        System.out.println("============Checking containsValue method======");
        System.out.println(map.containsValue(1));

        System.out.println("============Checking entrySet method===========");
        System.out.println(map.entrySet());

        System.out.println("============Checking size method===============");
        System.out.println(map.size());

        System.out.println("============Checking isEmpty method============");
        System.out.println(map.isEmpty());

        map.clear();
        System.out.println(map.isEmpty());





    }
}