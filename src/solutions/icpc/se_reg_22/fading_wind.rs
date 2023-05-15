use std::cmp;
use std::io;

pub fn main() {
    let (mut h, k, mut v, mut s) = {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let params: Vec<i32> = buffer
            .trim()
            .split(' ')
            .map(|s| s.parse::<i32>().unwrap())
            .collect();
        (params[0], params[1], params[2], params[3])
    };
    let mut h_dist = 0;
    while h > 0 {
        v += s;
        v -= cmp::max(1, v / 10);
        if v >= k {
            h += 1;
        }
        if 0 < v && v < k {
            h -= 1;
            if h == 0 {
                v = 0;
            }
        }
        if v <= 0 {
            h = 0;
            v = 0;
        }
        h_dist += v;
        if s > 0 {
            s -= 1;
        }
    }
    println!("{}", h_dist);
}
