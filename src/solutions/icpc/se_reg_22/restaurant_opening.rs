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

pub fn main_n2() {
    let (n, m) = {
        let mut buffer = String::new();
        io::stdin().read_line(&mut buffer).unwrap();
        let params: Vec<usize> = buffer
            .trim()
            .split(' ')
            .map(|s| s.parse::<usize>().unwrap())
            .collect();
        (params[0], params[1])
    };

    let mut row_sums = [0u16; 50];
    let mut col_sums = [0u16; 50];
    let mut grid_sum = 0u32;
    let mut cost_i = 0u32;
    let mut buffer = String::new();
    for i in 0..n {
        io::stdin().read_line(&mut buffer).unwrap();
        buffer
            .trim()
            .split(' ')
            .map(|s| s.parse::<u8>().unwrap())
            .enumerate()
            .take(m)
            .for_each(|(j, g_ij)| {
                row_sums[i] += g_ij as u16;
                col_sums[j] += g_ij as u16;
                grid_sum += g_ij as u32;
                cost_i += ((i as u32) + (j as u32)) * (g_ij as u32);
            });
        buffer.clear();
    }

    let mut min_cost = cost_i;

    cost_i += grid_sum; // avoids needing to use conditional branching in the loops

    let mut lhs_i = 0u32;
    for i in 0..n {
        cost_i = cost_i + 2 * lhs_i - grid_sum;

        let mut cost_j = cost_i + grid_sum;
        let mut lhs_j = 0u32;
        for j in 0..m {
            cost_j = cost_j + 2 * lhs_j - grid_sum;
            min_cost = cmp::min(min_cost, cost_j);
            lhs_j += col_sums[j] as u32;
        }

        lhs_i += row_sums[i] as u32;
    }

    println!("{}", min_cost);
}
