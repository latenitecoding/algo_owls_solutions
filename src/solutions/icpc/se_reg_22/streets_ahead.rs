use std::{collections::HashMap, fmt::Debug, io, str::FromStr};

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
    let (n, q) = next_tuple::<i32>(&mut String::new());
    let mut street_map = HashMap::new();

    let mut buffer = String::new();
    for i in 0..n {
        io::stdin().read_line(&mut buffer).unwrap();
        street_map.insert(buffer.trim().to_string(), i);
        buffer.clear();
    }

    for _ in 0..q {
        let streets = next_tuple::<String>(&mut buffer);
        let start = *street_map.get(&streets.0).unwrap();
        let end = *street_map.get(&streets.1).unwrap();
        println!("{}", (end - start).abs() - 1);
        buffer.clear();
    }
}
