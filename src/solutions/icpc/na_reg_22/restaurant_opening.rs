use std::{cmp, fmt::Debug, io, str::FromStr};

//============================================
// StdIn Helpers
//============================================

#[allow(unused)]
#[inline(always)]
fn next<T: FromStr>(buffer: &mut String) -> T
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    buffer.trim().parse::<T>().unwrap()
}

#[allow(unused)]
#[inline(always)]
fn next_tuple<T: FromStr>(buffer: &mut String) -> (T, T)
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    let mut iter = buffer.trim().split(' ').map(|s| s.parse::<T>().unwrap());
    (iter.next().unwrap(), iter.next().unwrap())
}

#[allow(unused)]
#[inline(always)]
fn next_vec<T: FromStr>(buffer: &mut String) -> Vec<T>
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    buffer
        .trim()
        .split(' ')
        .map(|s| s.parse::<T>().unwrap())
        .collect::<Vec<T>>()
}

//============================================
// Solution
//============================================

pub fn main() {
    let (n, m) = next_tuple::<i32>(&mut String::new());
    let mut grid = [[0; 50]; 50];

    let mut buffer = String::new();
    for grid_i in grid.iter_mut().take(n as usize) {
        let gs = next_vec::<i32>(&mut buffer);
        grid_i[..(m as usize)].copy_from_slice(&gs[..(m as usize)]);
        buffer.clear();
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
    let (n, m) = next_tuple::<usize>(&mut String::new());

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
