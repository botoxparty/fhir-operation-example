#!/usr/bin/env python3

import json
import os
import requests

base_url = os.environ['BASE_URL']
token = os.environ['TOKEN']


def upload(directory: str, resource_type: str):
    for entry in os.scandir(directory):
        if not (entry.is_file() and entry.name.endswith(".json")):
            continue
        print(f"Processing {entry.name}")
        
        headers = {
            "Authorization": f"Bearer {token}",
            "Content-Type": "application/fhir+json;charset=UTF-8"
        }

        with open(entry, mode="r", encoding="utf-8") as source:
            data = source.read().encode('utf8')
            read_id = json.loads(data)['id']

        requests.put(f"{base_url}/{resource_type}/{read_id}", data, headers=headers)


if __name__ == '__main__':
    upload("./dms-ig/profiles", "StructureDefinition")
    upload("./dms-ig/parameters", "SearchParameter")
    upload("./dms-ig/values", "ValueSet")
    print("Done!")
