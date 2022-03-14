import React from 'react';
import { useFragment } from 'react-relay/hooks';
import { graphql, commitMutation } from 'react-relay';
import RelayEnvironment from '../../RelayEnvironment';
import './ListInputControlItem.css';

const {ConnectionHandler} = require('relay-runtime');

const itemFragment = graphql`
    fragment ListInputControlItem_item on Item {
        id
        message
    }
`

const removeItemMutation = graphql`
    mutation ListInputControlItemRemoveItemMutation($id: ID!) {
        deleteItem(id: $id)
    }
`

function ListInputControlItem({
    itemsQueryRef,
    connectionId,
    disabled,
    onRemoveItem,
}) {
    const itemNode = useFragment(itemFragment, itemsQueryRef);

    const onRemoveItemEvent = () => {
        if (disabled === false) {
            commitMutation(RelayEnvironment, {
                mutation: removeItemMutation,
                variables: {
                    id: itemNode.id
                },
                onCompleted: response => {
                    onRemoveItem();
                },
                updater: store => {
                    const connectionRecord = store.get(connectionId);
                    ConnectionHandler.deleteNode(
                        connectionRecord,
                        itemNode.id,
                    );
                }
            });
        }
    }

    return (
        <div className="list_input_control_item_container">
            <div>{itemNode.message}</div>
            {!disabled && <div className="list_input_control_item_remove_button" onClick={onRemoveItemEvent}>x</div>}
        </div>
    );
}

export default ListInputControlItem;