use std::io;

pub fn main() {
    let (r, f) = {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let params: Vec<i32> = buffer
            .trim()
            .split(' ')
            .map(|s| s.parse::<i32>().unwrap())
            .collect();
        (params[0], params[1])
    };
    let d = if (f % r) > r / 2 { f / r + 1 } else { f / r };
    if d % 2 == 0 {
        println!("up");
    } else {
        println!("down");
    }
}
