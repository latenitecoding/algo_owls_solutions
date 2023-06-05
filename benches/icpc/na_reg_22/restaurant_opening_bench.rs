use criterion::{criterion_group, criterion_main, Criterion};
use rand::{thread_rng, Rng};
use std::cmp;

fn n4_solve(grid: &[[u8; 50]; 50], n: usize, m: usize) -> u32 {
    let mut min_cost = i32::MAX;
    for r_1 in 0..(n as i32) {
        for c_1 in 0..(m as i32) {
            let mut cost = 0;
            for r_2 in 0..(n as i32) {
                for c_2 in 0..(m as i32) {
                    let dist = (r_1 - r_2).abs() + (c_1 - c_2).abs();
                    cost += dist * (grid[r_2 as usize][c_2 as usize] as i32);
                }
            }
            min_cost = cmp::min(min_cost, cost);
        }
    }
    min_cost as u32
}

fn n2_solve(grid: &[[u8; 50]; 50], n: usize, m: usize) -> u32 {
    let mut row_sums = [0u16; 50];
    let mut col_sums = [0u16; 50];
    let mut grid_sum = 0u32;
    let mut cost_i = 0u32;
    for (i, grid_i) in grid.iter().enumerate().take(n) {
        grid_i.iter().enumerate().take(m).for_each(|(j, &g_ij)| {
            row_sums[i] += g_ij as u16;
            col_sums[j] += g_ij as u16;
            grid_sum += g_ij as u32;
            cost_i += ((i as u32) + (j as u32)) * (g_ij as u32);
        });
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

    min_cost
}

fn criterion_benchmark(c: &mut Criterion) {
    let mut rng = thread_rng();
    let (n, m) = (50usize, 50usize);
    let mut grid = [[0u8; 50]; 50];
    for i in 0..n {
        for j in 0..m {
            grid[i][j] = rng.gen_range(1..=50);
        }
    }
    c.bench_function("n4 solve", |b| b.iter(|| n4_solve(&grid, n, m)));
    c.bench_function("n2 solve", |b| b.iter(|| n2_solve(&grid, n, m)));
}

criterion_group!(benches, criterion_benchmark);
criterion_main!(benches);
