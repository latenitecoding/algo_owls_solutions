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
fn next_tuple<T: FromStr>(buffer: &mut String) -> (T, T, T, T)
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    let mut iter = buffer.trim().split(' ').map(|s| s.parse::<T>().unwrap());
    (
        iter.next().unwrap(),
        iter.next().unwrap(),
        iter.next().unwrap(),
        iter.next().unwrap(),
    )
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
    let (mut h, k, mut v, mut s) = next_tuple::<i32>(&mut String::new());
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
