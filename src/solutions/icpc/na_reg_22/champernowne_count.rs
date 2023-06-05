use std::{fmt::Debug, io, str::FromStr};

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
    let (n, k) = next_tuple::<u32>(&mut String::new());

    let mut c = 0u64;
    let mut shift = 10u32;

    let mut count = 0;
    for i in 1..=n {
        if i == shift {
            shift *= 10;
        }
        c = ((c * shift as u64) + (i as u64)) % (k as u64);
        if c == 0 {
            count += 1;
        }
    }
    println!("{}", count);
}
