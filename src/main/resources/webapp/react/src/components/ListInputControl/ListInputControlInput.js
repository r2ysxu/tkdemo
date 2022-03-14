import React from 'react';
import { useState } from 'react';
import { graphql, commitMutation } from 'react-relay';
import RelayEnvironment from '../../RelayEnvironment';
import './ListInputControlInput.css'

const {ConnectionHandler} = require('relay-runtime');

const createItemMutation = graphql`
    mutation ListInputControlInputAddItemMutation($message: String!) {
        createItem(message: $message) {
            node {
                ...ListInputControlItem_item
            }
        }
    }
`

function ListInputControlInput({
    placeholder,
    connectionId,
    disabled,
    onAddItem,
}) {
    const [message, setMessage] = useState("");

    const onAddItemEvent = event => {
        if (event.key === 'Enter' && message.length > 0) {
            commitMutation(RelayEnvironment, {
                mutation: createItemMutation,
                variables: {
                    message
                },
                onCompleted: response => {
                    setMessage("");
                    onAddItem();
                },
                updater: store => {
                    const connectionRecord = store.get(connectionId);
                    const serverEdge = store.getRootField('createItem');
                    const newEdge = ConnectionHandler.buildConnectionEdge(
                        store,
                        connectionRecord,
                        serverEdge,
                    );
                    ConnectionHandler.insertEdgeAfter(
                        connectionRecord,
                        newEdge,
                    );
                }
            });
        }
    }

    const onChange = (event) => {
        setMessage(event.target.value);
    }

    return (
        <div className="list_input_controls_input">
            <input
                type="text"
                placeholder={placeholder}
                onKeyPress={onAddItemEvent}
                value={message}
                onChange={onChange}
                disabled={disabled} />
        </div>
    );
}

export default ListInputControlInput;