import os
import json
import subprocess
from collections import deque

test_files_path = "./end_to_end_tests"
test_files = ["test1.json", "test2.json", "test3.json"]
directions = ['north', 'east', 'south', 'west']


def read_input_vehicles(path):
    queues = {}
    for direction in directions:
        queues[direction] = deque()
    with open(path, 'r') as file:
        json_data = json.load(file)
        for event in json_data['commands']:
            if event['type'] == 'step':
                continue
            queues[event['startRoad']].append(event['vehicleId'])
    return queues


def read_output_vehicles(path):
    cars = []
    with open(path, 'r') as file:
        json_data = json.load(file)
        for event in json_data['stepStatuses']:
            for car in event['leftVehicles']:
                cars.append(car)
    return cars


def verify_order(queues, cars):
    for car in cars:
        for _, queue in queues.items():
            if len(queue) > 0 and queue[0] == car:
                queue.popleft()
                break
        else:
            raise RuntimeError(
                f"Vehicle {car} is either out of order, appears multiple times or is not a vehicleId at all.")
    for _, queue in queues.items():
        if len(queue) > 0:
            raise RuntimeError(f"Vehicle {queue[0]} did not pass.")


def run_test(path: str):
    output_path = path.replace(".json", ".out")
    result = subprocess.run(["./run.sh", path, output_path])
    if result.returncode != 0:
        raise RuntimeError(f"Run retrun code is {result.returncode}.")

    queues = read_input_vehicles(path)
    cars = read_output_vehicles(output_path)
    verify_order(queues, cars)


if __name__ == "__main__":
    succeeded, failed = 0, 0
    for test in test_files:
        test_path = os.path.join(test_files_path, test)
        print(f"Running test {test_path}.")
        try:
            run_test(test_path)
            print("Test succeeded.")
            succeeded += 1
        except Exception as e:
            print("Test failed:")
            print(e)
            failed += 1
    print()
    print(f"Total end-to-end tests passed: {succeeded}, failed: {failed}")

