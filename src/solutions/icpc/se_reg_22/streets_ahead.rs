use std::collections::HashMap;
use std::io;

pub fn main() {
    let (n, q) = {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let params: Vec<i32> = buffer
            .trim()
            .split(' ')
            .map(|s| s.parse::<i32>().unwrap())
            .collect();
        (params[0], params[1])
    };

    let mut street_map = HashMap::new();

    for i in 0..n {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        street_map.insert(buffer.trim().to_string(), i);
    }

    for _ in 0..q {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let streets: Vec<String> = buffer
            .trim()
            .split(' ')
            .map(|s| s.trim().to_string())
            .collect();
        let start = *street_map.get(&streets[0]).unwrap();
        let end = *street_map.get(&streets[1]).unwrap();
        println!("{}", (end - start).abs() - 1);
    }
}
