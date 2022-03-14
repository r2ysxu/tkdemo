import React from 'react';
import { useState } from 'react';
import { graphql } from 'react-relay';
import { loadQuery, usePreloadedQuery, usePaginationFragment } from 'react-relay/hooks';
import RelayEnvironment from '../../RelayEnvironment';
import ListInputControlItem from './ListInputControlItem';
import ListInputControlInput from './ListInputControlInput';
import ScrollLoader from './ScrollLoader';
import './ListInputControl.css';

const PAGE_SIZE = 10;
const SCOLL_LOAD_HEIGHT = 50;

const itemsQuery = graphql`
    query ListInputControlQuery($count: Int!, $cursor: ID) {
        ...ListInputControl_items
    }
`;

const itemsFragment = graphql`
    fragment ListInputControl_items on Query
    @refetchable(queryName: "ListInputControlQueryPagination") {
        items(first: $count, after: $cursor)
        @connection(key: "ListInputControl_query_items") {
            __id
            total
            edges {
                node {
                    ...ListInputControlItem_item
                }
            }
        }
    }
`

const itemQueryRef = loadQuery(
  RelayEnvironment,
  itemsQuery,
  { count: PAGE_SIZE, cursor: null }
);

function ListInputControl({
    label,
    placeholder,
    required,
    disabled,
    max
}) {

    const query = usePreloadedQuery(itemsQuery, itemQueryRef);
    const {data, loadNext} = usePaginationFragment(
        itemsFragment, query
    );

    const pageInfo = data.items.pageInfo;
    const itemNodes = data.items.edges;
    const [totalItems, setTotalItems] = useState(data.items.total);

    const loadMore = () => {
        loadNext(PAGE_SIZE);
    }

    return (
        <div className="list_input_controls_container">
            <h2>{label}</h2>
            <span className={(required && totalItems === 0) ? "list_input_controls_error" : "list_input_controls_error_hide" }>
                Require at least one item
            </span>
            <ListInputControlInput
                placeholder={placeholder}
                connectionId={data.items.__id}
                disabled={disabled || totalItems >= max}
                onAddItem={() => {
                    setTotalItems(totalItems + 1);
                }}
            />
            <ScrollLoader loadMore={loadMore} hasMore={pageInfo.hasNextPage} height={SCOLL_LOAD_HEIGHT}>
                {(itemNodes || []).map( (item, index) =>
                    <ListInputControlItem
                        key={index}
                        itemsQueryRef={item.node}
                        connectionId={data.items.__id}
                        disabled={disabled}
                        onRemoveItem={() => {
                            setTotalItems(totalItems - 1);
                        }}
                    /> )}
            </ScrollLoader>
        </div>
    );
}

export default ListInputControl;