import random

def get_random_vector(min_x, max_x, min_y, max_y, min_z, max_z):
    x = random.uniform(min_x, max_x)
    y = random.uniform(min_y, max_y)
    z = random.uniform(min_z, max_z)
    return [x, y, z]