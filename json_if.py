import json

org_dict = {
    "input": {
        "a": 'abc',
        "b": 11
    },
    "statement": {
        "type": "if",
        "condition": {
            "type": "logical",
            "operator": "&&",
            "conditions": [
                {
                    "type": "comparison",
                    "input": "a",
                    "operator": "==",
                    "variable": 'abc'
                },
                {
                    "type": "comparison",
                    "input": "b",
                    "operator": ">",
                    "variable": 4
                }
            ],
        },
        "trueBranch": {
            "type": "return",
            "value": True
        },
        "falseBranch": {
            "type": "if",
            "condition": {
                "type": "comparison",
                "input": "b",
                "operator": "<",
                "variable": 10
            },
            "trueBranch": {
                "type": "return",
                "value": True
            },
            "falseBranch": {
                "type": "return",
                "value": False
            },
        }
    }
}

# if ( (a=='abc' && b >4) || c==0 || (e ==-1 && f == 1) {
#    if( d> 100) {
#       true
#    else
#       false
# else
#    if (a != 'abc' && b<=4)
#       true
#    else
#       false

dict = {
    "input": {
        "a": 'abcc',
        "b": 7,
        "c": 1,
        "d": 101,
        "e": -1,
        "f": 1
    },
    "statement": {
        "type": "if",
        "condition": {
            "type": "logical",
            "operator": "||",
            "conditions": [
                {
                    "type": "logical",
                    "operator": "&&",
                    "conditions": [
                        {
                            "type": "comparison",
                            "input": "a",
                            "operator": "==",
                            "variable": "abc"
                        },
                        {
                            "type": "comparison",
                            "input": "b",
                            "operator": ">",
                            "variable": 4
                        }
                    ]
                },
                {
                    "type": "comparison",
                    "input": "c",
                    "operator": "==",
                    "variable": 0
                },
                {
                    "type": "logical",
                    "operator": "&&",
                    "conditions": [
                        {
                            "type": "comparison",
                            "input": "e",
                            "operator": "==",
                            "variable": -1
                        },
                        {
                            "type": "comparison",
                            "input": "f",
                            "operator": "==",
                            "variable": 1
                        }
                    ]
                },
            ]
        },
        "trueBranch": {
            "statement": {
                "type": "if",
                "condition": {
                    "type": "comparison",
                    "input": "d",
                    "operator": ">",
                    "variable": 100
                },
                "trueBranch": {
                    "statement": None,
                    "returnValue": True
                },
                "falseBranch": {
                    "statement": None,
                    "returnValue": False
                }
            },
            "returnValue": None
        },
        "falseBranch": {
            "statement": {
                "type": "if",
                "condition": {
                    "type": "logical",
                    "operator": "&&",
                    "conditions": [
                        {
                            "type": "comparison",
                            "input": "a",
                            "operator": "!=",
                            "variable": "abc"
                        },
                        {
                            "type": "comparison",
                            "input": "b",
                            "operator": "<=",
                            "variable": 4
                        }
                    ]
                },
                "trueBranch": {
                    "statement": None,
                    "returnValue": True
                },
                "falseBranch": {
                    "statement": None,
                    "returnValue": False
                }
            },
            "returnValue": None
        }
    }
}


def process_payload(payload):
    inputs = payload['input']
    statement = payload['statement']

    return process_statement(statement, inputs)


def process_statement(statement, inputs):
    return process_if_statement(statement, inputs)


def process_comparison(condition, inputs):
    operator = condition['operator']
    input = inputs[condition['input']]
    if operator == '>':
        return input > condition['variable']
    elif operator == '>=':
        return input >= condition['variable']
    elif operator == '<':
        return input < condition['variable']
    elif operator == '<=':
        return input <= condition['variable']
    elif operator == '==':
        return input == condition['variable']
    elif operator == '!=':
        return input != condition['variable']
    raise Exception("Unsupported operator")


def process_logical(logical_condition, inputs):
    if logical_condition['operator'] == '&&':
        result = None
        for condition in logical_condition['conditions']:
            current_result = process_comparison(condition, inputs)
            if result is None:
                if not current_result:
                    return current_result
                else:
                    result = current_result
            else:
                result &= current_result
                if not result:
                    return result
        return result
    elif logical_condition['operator'] == '||':
        for condition in logical_condition['conditions']:
            if process_condition(condition, inputs):
                return True
        return False


def process_condition(condition, inputs):
    type = condition['type']
    if type == 'comparison':
        return process_comparison(condition, inputs)
    elif type == 'logical':
        return process_logical(condition, inputs)
    elif type == 'if':
        return process_if_statement(condition, inputs)


def process_if_statement(statement, inputs):
    if not statement['type'] == 'if':
        raise Exception("Cannot process statement as it's not if")

    result = process_condition(statement['condition'], inputs)

    if result:
        if statement['trueBranch']['statement']:
            return process_if_statement(statement['trueBranch']['statement'], inputs)
        else:
            return statement['trueBranch']['returnValue']
    else:
        if statement['falseBranch']['statement']:
            return process_if_statement(statement['falseBranch'], inputs)
        else:
            return statement['falseBranch']['returnValue']


# print(process_payload(dict))

print(json.dumps(dict,indent=2))
