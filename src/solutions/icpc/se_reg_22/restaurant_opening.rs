use std::cmp;
use std::io;

pub fn main() {
    let (n, m) = {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let params: Vec<i32> = buffer
            .trim()
            .split(' ')
            .map(|s| s.parse::<i32>().unwrap())
            .collect();
        (params[0], params[1])
    };
    let mut grid = [[0; 50]; 50];
    for grid_i in grid.iter_mut().take(n as usize) {
        let gs: Vec<i32> = {
            let mut buffer = String::new();
            io::stdin().read_line(&mut buffer).unwrap();
            buffer
                .trim()
                .split(' ')
                .map(|s| s.parse::<i32>().unwrap())
                .collect()
        };
        grid_i[..(m as usize)].copy_from_slice(&gs[..(m as usize)]);
    }
    let mut min_cost = i32::MAX;
    for r_1 in 0..n {
        for c_1 in 0..m {
            let mut cost = 0;
            for r_2 in 0..n {
                for c_2 in 0..m {
                    let dist = (r_1 - r_2).abs() + (c_1 - c_2).abs();
                    cost += dist * grid[r_2 as usize][c_2 as usize];
                }
            }
            min_cost = cmp::min(min_cost, cost);
        }
    }
    println!("{}", min_cost);
}
